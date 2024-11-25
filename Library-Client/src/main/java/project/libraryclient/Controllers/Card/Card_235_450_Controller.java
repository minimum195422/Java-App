package project.libraryclient.Controllers.Card;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.libraryclient.Class.Book;

public class Card_235_450_Controller {

    @FXML
    private ImageView BookCover;
    @FXML
    private Label BookName;
    @FXML
    private Label AuthorName;

    public void setBookInfo(Book book) {
        BookCover.setImage(new Image(book.getImagePreview()));
        BookName.setText(book.getTitle());
        AuthorName.setText(book.getAuthor());
    }
}
