package Frontend.Controller;

import Frontend.Class.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class BookCellController {
    @FXML
    private GridPane bookLists;
    @FXML
    private ImageView image;
    @FXML
    private Text titleText;
    @FXML
    private Text authorText;
    @FXML
    private Text publishedDateText;
    @FXML
    private Text ISBN13Text;
    @FXML
    private Text priceText;

    public BookCellController() {
        try {
            FXMLLoader parent = new FXMLLoader((getClass().getResource("/Frontend/BookCell.fxml")));
            parent.setController(this);
            bookLists = parent.load();
        } catch (IOException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public void setBookInfo(Book book) {
        try {
            image.setImage(new Image(book.getImagePreview()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            titleText.setText(book.getTitle());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            authorText.setText(book.getAuthor());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            publishedDateText.setText(book.getPublishedDate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            ISBN13Text.setText(book.getISBN_13());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            priceText.setText("" + book.getPrice());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public GridPane getBookInfo() {
        return bookLists;
    }
}
