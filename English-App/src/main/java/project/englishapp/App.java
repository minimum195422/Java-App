package project.englishapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXML/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 670, 430);
        stage.initStyle(StageStyle.TRANSPARENT); // Cửa sổ trong suốt
        scene.setFill(null); // Làm cho nền trong suốt
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}