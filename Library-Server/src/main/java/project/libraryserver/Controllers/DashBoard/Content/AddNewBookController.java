package project.libraryserver.Controllers.DashBoard.Content;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewBookController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SetDefault();
    }

    @FXML
    VBox ListLog;
    public void SetDefault() {
        Label label = new Label();
        label.setText("add book");
        label.setPrefSize(1000, 30);
        label.setMinSize(1000, 30);
        label.setMaxSize(1000, 30);
        ListLog.getChildren().add(label);
    }
}
