package project.englishapp.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import project.englishapp.Consts.DashBoardData;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {

    @FXML
    public Button DashBoard_HomeButton;

    @FXML
    public BorderPane DashBoard_BorderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
