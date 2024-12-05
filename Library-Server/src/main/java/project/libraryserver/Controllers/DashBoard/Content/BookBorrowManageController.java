package project.libraryserver.Controllers.DashBoard.Content;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import project.libraryserver.Consts.DATA;
import project.libraryserver.Consts.JsonType;
import project.libraryserver.Controllers.Card.BorrowBookCardController;
import project.libraryserver.Models.JsonFileHandler;

import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ResourceBundle;

public class BookBorrowManageController implements Initializable {

    @FXML
    public VBox jsonDisplay;

    private WatchService watchService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadDisplayListBorrow();

        try {
            watchService = FileSystems.getDefault().newWatchService();
            Paths.get(DATA.SERVER_LOG_FILE).getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            // Tạo một thread để theo dõi sự thay đổi của file log
            Thread watchThread = new Thread(this::watchBorrowJsonFile);
            watchThread.setDaemon(true);
            watchThread.start();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private void LoadDisplayListBorrow() {
        if (!jsonDisplay.getChildren().isEmpty()) jsonDisplay.getChildren().clear();

        JSONArray jsonArray = JsonFileHandler.getInstance().ReadJsonArray();

        if (jsonArray.isEmpty()) return;

        for (int i = 0; i < jsonArray.length(); ++i) {
            JsonType type = JsonType.valueOf(jsonArray.getJSONObject(i).getString("status"));
            if (type == JsonType.PENDING || type == JsonType.BORROW_ACCEPTED) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.BORROW_BOOK_CARD));
                    AnchorPane pane = loader.load();
                    BorrowBookCardController controller = loader.getController();
                    controller.setInfo(jsonArray.getJSONObject(i));
                    jsonDisplay.getChildren().add(pane);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void watchBorrowJsonFile() {
        try {
            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        // Kiểm tra xem file log có thay đổi
                        if (event.context().toString().equals(
                                Paths.get(DATA.SERVER_BORROW_JSON_FILE).getFileName().toString())) {
                            // cập nhật giao diện
                            Platform.runLater(this::LoadDisplayListBorrow);
                        }
                    }
                }
                key.reset();
            }
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }
    }
}
