package project.englishapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import project.englishapp.Controllers.LoginController;
import project.englishapp.Models.SceneManager;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXML/Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(null);
            stage.setScene(scene);
            stage.show();

            LoginController loginController = fxmlLoader.getController();
            loginController.setStage(stage);
        }
        catch (IOException e) {
            System.out.println("Fail to load FXML file");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}