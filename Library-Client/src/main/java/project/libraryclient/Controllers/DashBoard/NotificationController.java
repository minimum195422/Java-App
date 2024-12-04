package project.libraryclient.Controllers.DashBoard;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Models.NotificationHandler;

import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ResourceBundle;

public class NotificationController implements Initializable {

    private static final Log log = LogFactory.getLog(NotificationController.class);
    public VBox DisplayNotification;

    private WatchService watchService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadDisplayNotification();

        try {
            watchService = FileSystems.getDefault().newWatchService();
            Paths.get(DATA.NOTIFICATION_FILE).getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            // Tạo một thread để theo dõi sự thay đổi của file log
            Thread watchThread = new Thread(this::watchLogFile);
            watchThread.setDaemon(true);
            watchThread.start();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private void LoadDisplayNotification() {
        if (!DisplayNotification.getChildren().isEmpty()) DisplayNotification.getChildren().clear();

        for (String s : NotificationHandler.getInstance().readFile()) {
            Label label = new Label(s);
            label.setMaxWidth(1000);
            label.setWrapText(true);
            label.setStyle("-fx-font-size: 16px;");
            DisplayNotification.getChildren().add(label);
            DisplayNotification.getChildren().add(new Separator());
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
                                Paths.get(DATA.NOTIFICATION_FILE).getFileName().toString())) {
                            Platform.runLater(this::LoadDisplayNotification);
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
