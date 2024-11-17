package project.libraryserver.Models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class SceneHandler {
    private static SceneHandler sceneHandler;

    private static Stage MainStage;

    private static Class<?> MainApp;

    private final HashMap<String, Object> controller = new HashMap<>();

    private final HashMap<String, Scene> scenes = new HashMap<>();

    public SceneHandler(Class<?> app, Stage stage) {
        MainStage = stage;
        MainApp = app;
    }

    public static SceneHandler getInstance(Class<?> app, Stage stage) {
        if (sceneHandler == null) {
            sceneHandler = new SceneHandler(app, stage);
        }
        return sceneHandler;
    }

    public Scene AddScene(String SceneKey, String Location) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.getResource(Location));
        Scene scene = new Scene(fxmlLoader.load());
        scene.setFill(null);

        scenes.put(SceneKey, scene);
        controller.put(SceneKey, fxmlLoader.getController());

        return scene;
    }

    public void SetScene(String SceneKey) {
        if (scenes.get(SceneKey) != null) {

            MainStage.setScene(scenes.get(SceneKey));
        }
    }
}
