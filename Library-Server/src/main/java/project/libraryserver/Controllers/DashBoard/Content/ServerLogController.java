package project.libraryserver.Controllers.DashBoard.Content;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import project.libraryserver.Consts.DATA;
import project.libraryserver.Server.ServerLog;

import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ServerLogController implements Initializable {

    @FXML
    public VBox ListLog;

    private WatchService watchService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadDisplayListLog();

        try {
            watchService = FileSystems.getDefault().newWatchService();
            Paths.get(DATA.SERVER_LOG_FILE).getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            // Tạo một thread để theo dõi sự thay đổi của file log
            Thread watchThread = new Thread(this::watchLogFile);
            watchThread.setDaemon(true);
            watchThread.start();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private void LoadDisplayListLog() {
        if (!ListLog.getChildren().isEmpty()) ListLog.getChildren().clear();
        for (String s : ServerLog.getInstance().readLog()) {
            Label label = new Label(s);
            ListLog.getChildren().add(label);
            ListLog.getChildren().add(new Separator());
        }
    }

    private void watchLogFile() {
        try {
            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        // Kiểm tra xem file log có thay đổi
                        if (event.context().toString().equals(
                                Paths.get(DATA.SERVER_LOG_FILE).getFileName().toString())) {
                            Platform.runLater(this::LoadDisplayListLog);
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

