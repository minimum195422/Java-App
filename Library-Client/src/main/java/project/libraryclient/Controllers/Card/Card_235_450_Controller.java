package project.libraryclient.Controllers.Card;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import project.libraryclient.Class.Book;
import project.libraryclient.Consts.DATA;

import java.io.IOException;

public class Card_235_450_Controller {

    @FXML
    private AnchorPane book;
    @FXML
    private ImageView BookCover;
    @FXML
    private Label BookName;
    @FXML
    private Label AuthorName;

    public void setBookInfo(Book book) {
        try {
            BookCover.setImage(new Image(book.getImagePreview()));
            BookCover.setFitWidth(180);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            BookName.setText(book.getTitle());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            AuthorName.setText(book.getAuthor());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public AnchorPane getBookInfo() {
        return book;
    }

    public Card_235_450_Controller() {
        BookCover = new ImageView();
        AuthorName = new Label();
        BookName = new Label();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.CARD_235_450));
            loader.setController(this);
            book = loader.load();
        } catch (IOException ex) {
            System.err.print(ex.getMessage());
        }
    }
}
