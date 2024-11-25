package project.libraryserver;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.libraryserver.Consts.DATA;
import project.libraryserver.Database.MySql;
import project.libraryserver.Models.SceneHandler;
import project.libraryserver.Server.Server;

import java.util.ArrayList;

public class App extends Application {

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

        // Start server
        server = Server.getInstance();

        // start database connection
        mysql = MySql.getInstance();

        // SceneHandler initialize
        sceneHandler = SceneHandler.getInstance(App.class, stage);

        DashBoard = sceneHandler.AddScene(DATA.SCENE_DASHBOARD_PAGE,"FXML/DashBoard.fxml");

        sceneHandler.SetScene(DATA.SCENE_DASHBOARD_PAGE);

        stage.show();
    }
}