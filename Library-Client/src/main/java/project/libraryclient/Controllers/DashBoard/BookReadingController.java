package project.libraryclient.Controllers.DashBoard;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class BookReadingController {

    @FXML
    public AnchorPane readingPane;

    @FXML
    public Button CloseButton;


    public void CloseButtonClicked() {
        readingPane.getParent().setVisible(false);
        readingPane.getParent().setDisable(true);
    }
}
