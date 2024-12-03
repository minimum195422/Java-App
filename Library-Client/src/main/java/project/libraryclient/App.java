package project.libraryclient;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import project.libraryclient.Client.Client;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Database.MySql;
import project.libraryclient.Models.SceneHandler;

import java.io.IOException;

public class App extends Application {

    // Client
    Client client;

    // Connect to database
    MySql mysql;

    // Main scene handler
    SceneHandler sceneHandler;

    // List of scene in app
    Scene LoginPage, RegisterPage, BeingDev, HomePage, VerifyPage;

    @Override
    public void start(Stage stage) throws IOException{
        // start client - trong client đã chạy trên thread riêng
//        client = Client.getInstance();

        // Kết nối database trên một thread riêng
        new Thread(() -> mysql = MySql.getInstance()).start();

        // SceneHandler initialize
        sceneHandler = SceneHandler.getInstance(App.class, stage);

        // Load single scene to SceneHandler
        LoginPage = sceneHandler.AddScene(DATA.SCENE_LOGIN_PAGE, "FXML/Login.fxml");
        RegisterPage = sceneHandler.AddScene(DATA.SCENE_REGISTER_PAGE, "FXML/Register.fxml");
        BeingDev = sceneHandler.AddScene(DATA.SCENE_BEING_DEVELOPMENT, "FXML/BeingDevelop.fxml");
        HomePage = sceneHandler.AddScene(DATA.SCENE_DASHBOARD, "FXML/DashBoard.fxml");
        VerifyPage = sceneHandler.AddScene(DATA.SCENE_VERIFY_PAGE, "FXML/Verify.fxml");

        // Set popup scene when open app
        sceneHandler.SetScene(DATA.SCENE_DASHBOARD);

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.show();
    }

    @Override
    public void stop() throws Exception {
        if (client != null) {
            client.close();
        }
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}
