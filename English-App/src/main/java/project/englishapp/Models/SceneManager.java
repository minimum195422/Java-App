package project.englishapp.Models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import project.englishapp.App;

import java.io.IOException;

public class SceneManager {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.initStyle(StageStyle.TRANSPARENT);
    }
    
    public void switchScene(String Link) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(Link));
            Scene scene = new Scene(fxmlLoader.load(), 670, 430);
            scene.setFill(null);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            System.out.println("Fail to load FXML: " + Link);
            e.printStackTrace();
        }
    }

}
