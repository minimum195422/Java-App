package project.englishapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import project.englishapp.Consts.SceneData;
import project.englishapp.Models.SceneHandler;

import java.io.IOException;

public class App extends Application {
    SceneHandler sceneHandler;
    Scene LoginPage, RegisterPage, BeingDev, HomePage;

    @Override
    public void start(Stage stage) throws IOException {
        sceneHandler = SceneHandler.getInstance(App.class, stage);
        LoginPage = sceneHandler.AddScene(SceneData.SCENE_LOGIN_PAGE, "FXML/Login.fxml");
        RegisterPage = sceneHandler.AddScene(SceneData.SCENE_REGISTER_PAGE, "FXML/Register.fxml");
        BeingDev = sceneHandler.AddScene(SceneData.SCENE_BEING_DEVELOPMENT, "FXML/BeingDevelop.fxml");
        HomePage = sceneHandler.AddScene(SceneData.SCENE_HOME_PAGE, "FXML/Home.fxml");

        sceneHandler.SetScene(SceneData.SCENE_LOGIN_PAGE);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}