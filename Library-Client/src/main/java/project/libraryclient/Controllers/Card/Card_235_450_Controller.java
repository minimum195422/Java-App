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
        String URL = book.getImagePreview();
        try {
            BookCover.setImage(new Image(URL));
        } catch (Exception e) {
//            BookCover.setImage(new Image("../resources/project/libraryclient/Images/Icons/no-image.png"));
            BookCover.setImage(new Image("https://www.freeiconspng.com/thumbs/no-image-icon/no-image-icon-6.png"));
        }
        BookName.setText(book.getTitle());
        AuthorName.setText(book.getAuthorInString());
    }
}
