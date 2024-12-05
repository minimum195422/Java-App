package project.libraryclient.Controllers.Card;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import project.libraryclient.Book.Book;
import project.libraryclient.Consts.DATA;

import java.nio.file.Path;

public class Card_180_320_Controller {

    @FXML
    public ImageView BookCover;

    @FXML
    public Label BookName, AuthorsName;

    public void setInfo(Book book) {
        BookName.setText(book.getTitle());
        AuthorsName.setText(String.join(", ", book.getAuthors()));
        BookCover.setImage(book.getImagePreview());
    }

}
