package project.libraryserver.Controllers.DashBoard.Content;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageUsersController implements Initializable {
    @FXML
    VBox ListLog;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadfromlogfile();
    }

    public void loadfromlogfile() {
            Label label = new Label();
            label.setText("manage user page");
            label.setPrefSize(1000, 30);
            label.setMinSize(1000, 30);
            label.setMaxSize(1000, 30);
            ListLog.getChildren().add(label);
    }
}
