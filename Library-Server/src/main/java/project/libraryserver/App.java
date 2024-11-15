package project.libraryserver;

import javafx.application.Application;
import javafx.stage.Stage;
import project.libraryserver.Models.SceneHandler;

public class App extends Application {
    // Main scene handler
    SceneHandler sceneHandler;

    // List of scene in app


    @Override
    public void start(Stage stage) throws Exception {
        // SceneHandler initialize
        sceneHandler = SceneHandler.getInstance(App.class, stage);


    }
}
