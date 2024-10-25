package Frontend;

import Backend.MySQLConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Frontend.Library.SceneHandler;

import java.io.IOException;
import java.sql.Connection;

public class Main extends Application {

    SceneHandler sceneHandler;
    Scene LoginPage, RegisterPage, DashboardPage;
    public static Connection connection;
    @Override
    public void start(Stage stage) throws IOException {
        connection = MySQLConnection.connectToDB();
        sceneHandler = SceneHandler.getInstance(Main.class, stage);
        LoginPage = sceneHandler.addScene("LoginPage", "Login.fxml");
        RegisterPage = sceneHandler.addScene("RegisterPage", "Register.fxml");
        DashboardPage = sceneHandler.addScene("DashboardPage", "Dashboard.fxml");
        sceneHandler.setScene("DashboardPage");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}