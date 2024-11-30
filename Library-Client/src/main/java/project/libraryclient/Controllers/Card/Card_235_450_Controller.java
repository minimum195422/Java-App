package project.libraryclient.Controllers.Card;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.libraryclient.Book.Book;

import java.util.ArrayList;

public class Card_235_450_Controller {

    @FXML
    private ImageView BookCover;
    @FXML
    private Label BookName;
    @FXML
    private Label AuthorName;

    public void setBookCover(Image bookCover) {
        try {
            BookCover.setImage(bookCover);
        } catch (Exception e) {
//            BookCover.setImage(new Image("../resources/project/libraryclient/Images/Icons/no-image.png"));
            BookCover.setImage(new Image("https://www.freeiconspng.com/thumbs/no-image-icon/no-image-icon-6.png"));
        }
    }

    public void setBookName(String bookName) {
        BookName.setText(bookName);
    }

    public void setAuthorName(ArrayList<String> list) {
        AuthorName.setText(list.toString());
    }
}
