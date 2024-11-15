package project.libraryclient.Controllers.Card;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card_235_450_Controller {

    public ImageView BookCover;
    public Label BookName;
    public Label AuthorName;

    public void setBookCover(Image bookCover) {
        BookCover.setImage(bookCover);
        BookCover.setFitWidth(180);
    }

    public void setBookName(String bookName) {
        BookName.setText(bookName);
    }

    public void setAuthorName(String authorName) {
        AuthorName.setText(authorName);
    }
}
