package project.libraryserver.Controllers.Card;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class BookCard_600_200 {

    @FXML
    public ImageView BookCover;

    @FXML
    public Text TitleText, PublisherText,
            PublishedDateText, AuthorText, CategoriesText;

    public void setInfo(
            Image bookCover, String titleText, String publisherText,
            String categoriesText, String publishedDateText, String authorText) {
        BookCover.setImage(bookCover);
        TitleText.setText("Title: " + titleText);
        PublisherText.setText("Publisher: " + publisherText);
        CategoriesText.setText("Categories: " + categoriesText);
        PublishedDateText.setText("Published date: " + publishedDateText);
        AuthorText.setText("Authors: " + authorText);
    }
}
