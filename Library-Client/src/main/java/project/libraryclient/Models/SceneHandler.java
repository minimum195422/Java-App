package project.libraryclient.Models;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import project.libraryclient.Consts.DATA;

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
//            if (SceneKey.equals(DATA.SCENE_LOGIN_PAGE) ||
//                    SceneKey.equals(DATA.SCENE_REGISTER_PAGE)) {
//                MainStage.sizeToScene();
//                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//
////                // Tính toán vị trí trung tâm
////                double centerX = (screenBounds.getWidth() - MainStage.getWidth()) / 2;
////                double centerY = (screenBounds.getHeight() - MainStage.getHeight()) / 2;
////
////                // Đặt vị trí của Stage vào trung tâm màn hình
////                MainStage.setX(centerX);
////                MainStage.setY(centerY);
//            } else {
////                // Đặt vị trí vào góc màn hình
////                MainStage.setX(Screen.getPrimary().getVisualBounds().getMinX());
////                MainStage.setY(Screen.getPrimary().getVisualBounds().getMinY());
////
////                // Cài kích thước hiển thị bằng với màn hình
////                MainStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
////                MainStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
//            }
            MainStage.setScene(scenes.get(SceneKey));
        }
    }


}
