package Frontend;

import Backend.MySQLConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Frontend.Library.SceneHandler;

import java.io.IOException;
import java.sql.Connection;
import java.util.Objects;

public class Main extends Application {

    SceneHandler sceneHandler;
    Scene LoginPage, RegisterPage, DashboardPage, AddBooksPage;
    public static Connection connection;
    @Override
    public void start(Stage stage) throws IOException {
        connection = MySQLConnection.connectToDB();
        sceneHandler = SceneHandler.getInstance(Main.class, stage);
        LoginPage = sceneHandler.addScene("LoginPage", "Login.fxml");
        RegisterPage = sceneHandler.addScene("RegisterPage", "Register.fxml");
        DashboardPage = sceneHandler.addScene("DashboardPage", "Dashboard.fxml");
        AddBooksPage = sceneHandler.addScene("AddBooksPage", "AddBooks.fxml");
        LoginPage.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        RegisterPage.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        DashboardPage.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        AddBooksPage.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        System.out.println(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        sceneHandler.setScene("DashboardPage");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}