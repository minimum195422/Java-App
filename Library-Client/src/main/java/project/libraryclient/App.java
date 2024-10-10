package project.libraryclient;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import project.libraryclient.Consts.SceneData;
//import project.libraryclient.JDBC.AppJDBC;
import project.libraryclient.Models.SceneHandler;

import java.io.IOException;
import java.sql.SQLException;

public class App extends Application {
    // Define main SceneHandler
    SceneHandler sceneHandler;

    // List of scene
    Scene LoginPage, RegisterPage, BeingDev, HomePage, VerifyPage;

    // Database connection
//    AppJDBC appJDBC;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        // SceneHandler initialize
        sceneHandler = SceneHandler.getInstance(App.class, stage);

        // Load single scene to SceneHandler
        LoginPage = sceneHandler.AddScene(SceneData.SCENE_LOGIN_PAGE, "FXML/Login.fxml");
        RegisterPage = sceneHandler.AddScene(SceneData.SCENE_REGISTER_PAGE, "FXML/Register.fxml");
        BeingDev = sceneHandler.AddScene(SceneData.SCENE_BEING_DEVELOPMENT, "FXML/BeingDevelop.fxml");
        HomePage = sceneHandler.AddScene(SceneData.SCENE_DASHBOARD_PAGE, "FXML/DashBoard.fxml");
        VerifyPage = sceneHandler.AddScene(SceneData.SCENE_VERIFY_PAGE, "FXML/Verify.fxml");

        // Set popup scene when open app
        sceneHandler.SetScene(SceneData.SCENE_DASHBOARD_PAGE);

        // Connect database
//        appJDBC = AppJDBC.getInstance();

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
