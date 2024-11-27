package project.libraryserver.Controllers.Card;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import project.libraryserver.Book.Book;

public class BookCard_600_200 {

    @FXML
    public ImageView BookCover;

    @FXML
    public Text TitleText;

    @FXML
    public Text PublisherText;

    @FXML
    public Text ISBN13Text;

    @FXML
    public Text ISBN10Text;

    @FXML
    public Text PublishedDateText;

    @FXML
    public Text AuthorText;

    public void setInfor(
            Image bookCover, String titleText, String publisherText,
            String Isbn13Text, String Isbn10Text, String publishedDateText, String authorText) {
        BookCover.setImage(bookCover);
        TitleText.setText(titleText);
        PublisherText.setText(publisherText);
        ISBN10Text.setText(Isbn10Text);
        ISBN13Text.setText(Isbn13Text);
        PublisherText.setText(publishedDateText);
        AuthorText.setText(authorText);
    }
}
