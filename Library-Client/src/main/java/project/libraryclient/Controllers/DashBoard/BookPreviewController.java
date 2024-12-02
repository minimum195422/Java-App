package project.libraryclient.Controllers.DashBoard;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import project.libraryclient.Book.Book;

public class BookPreviewController {
    @FXML
    public AnchorPane MainPane;

    @FXML
    public Button ReturnButton;

    public void setInfor(Book book) {

    }

    public void ExitClicked() {
        MainPane.getParent().setVisible(false);
        MainPane.getParent().setDisable(true);
    }
}
