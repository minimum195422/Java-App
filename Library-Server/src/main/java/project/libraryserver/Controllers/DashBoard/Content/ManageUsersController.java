package project.libraryserver.Controllers.DashBoard.Content;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageUsersController implements Initializable {
    @FXML
    public VBox UserList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SetDefault();
    }

    public void SetDefault() {
        for (int i = 0; i < 30; ++i) {
            Label label = new Label();
            label.setText("User: " + i);
            label.setPrefSize(1000, 30);
            label.setMinSize(1000, 30);
            label.setMaxSize(1000, 30);
            UserList.getChildren().add(label);
        }
    }
}
