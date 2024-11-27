package project.libraryserver.Controllers.DashBoard.Content;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewBookController implements Initializable {

    @FXML
    public VBox DisplayListBook;

    @FXML
    public Button SearchButton;

    @FXML
    public ToggleButton SearchByAuthorButton;

    @FXML
    public ToggleButton SearchByTypeButton;

    @FXML
    public ToggleButton SearchByPublisherButton;

    @FXML
    public ToggleButton SearchBySubjectButton;

    @FXML
    public ToggleButton SearchByIsbnButton;

    public void SearchButtonClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Label label = new Label("dadf");
        label.setPrefSize(600, 200);
        label.setStyle("-fx-background-color: #000000;");
        DisplayListBook.getChildren().add(label);
    }
}
