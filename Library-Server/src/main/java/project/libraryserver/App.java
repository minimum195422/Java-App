package project.libraryserver;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import project.libraryserver.Consts.DATA;
import project.libraryserver.Database.MySql;
import project.libraryserver.Models.SceneHandler;
import project.libraryserver.Server.Server;
import project.libraryserver.Server.ServerLog;

import java.io.IOException;

public class App extends Application {

    // Book borrow manager
    BookBorrowManager bookBorrowManager;

    // Server log
    ServerLog serverLog;

    // Server
    Server server;

    // database connection
    MySql mysql;
    // Main scene handler
    SceneHandler sceneHandler;

    // List of scene in app
    Scene DashBoard;

    @Override
    public void start(Stage stage) throws Exception {

        // Tạo server log
        serverLog = ServerLog.getInstance();

        // Chạy server trên một thread riêng
        new Thread(() -> {
            try {
                server = Server.getInstance();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        // Kết nối database trên một thread riêng
        new Thread(() -> mysql = MySql.getInstance()).start();

        // Book borrow manager
        bookBorrowManager = BookBorrowManager.getInstance();

        // SceneHandler initialize
        sceneHandler = SceneHandler.getInstance(App.class, stage);

        DashBoard = sceneHandler.AddScene(DATA.SCENE_DASHBOARD_PAGE, DATA.DASHBOAD_LINK);

        sceneHandler.SetScene(DATA.SCENE_DASHBOARD_PAGE);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

}