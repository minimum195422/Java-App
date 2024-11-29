package project.libraryserver.Controllers.DashBoard.Content;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import project.libraryserver.API.GoogleBookAPI.BookAPI;
import project.libraryserver.Book.Book;
import project.libraryserver.Consts.SearchType;
import project.libraryserver.Database.MySql;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddNewBookController implements Initializable {

    @FXML
    public VBox DisplayListBook;

    @FXML
    public Button SearchButton;

    @FXML
    public TextField SearchQuery;

    @FXML
    public ToggleButton SearchByAuthorButton;

    @FXML
    public ToggleButton SearchByTitleButton;

    @FXML
    public ToggleButton SearchByPublisherButton;

    @FXML
    public ToggleButton SearchBySubjectButton;

    @FXML
    public ToggleButton SearchByIsbnButton;


    @FXML
    public Button AddNewBookButton;

    @FXML
    public Label SelectedBookId, SelectedBookTitle, SelectedBookAuthors,
            SelectedBookPublisher, SelectedBookPublishedDate, SelectedBookDescription,
            SelectedBookCategories, SelectedBookIsbn13, SelectedBookIsbn10,
            SelectedBookReadLink, Notification;
    @FXML
    public ImageView SelectedBookCover;
    Book newBook = new Book();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayListBook.setSpacing(100);
    }

    public void SearchAction() throws URISyntaxException, IOException {
        ArrayList<SearchType> types = new ArrayList<>();
        if (SearchByAuthorButton.isSelected()) types.add(SearchType.INAUTHOR);
        if (SearchByTitleButton.isSelected()) types.add(SearchType.INTITLE);
        if (SearchByPublisherButton.isSelected()) types.add(SearchType.INPUBLISHER);
        if (SearchBySubjectButton.isSelected()) types.add(SearchType.INSUBJECT);
        if (SearchByIsbnButton.isSelected()) types.add(SearchType.INISBN);
        if (SearchQuery.getText().isEmpty()) return;
        ArrayList<Book> list = BookAPI.SearchBook(SearchQuery.getText(), types);

        UpdateBookList(list);
    }

    public void UpdateBookList(ArrayList<Book> list) {
        if (!DisplayListBook.getChildren().isEmpty()) DisplayListBook.getChildren().clear();

        for (Book book : list) {
            DisplayListBook.getChildren().add(book.getDisplayCard());
//            try {
//                MySql.addNewBook(book);
//            } catch (SQLException _) {
//                System.out.println("Error while adding books");
//                System.out.println(book.toString());
//            }
            book.getDisplayCard().setOnMouseClicked(
                    _ -> {
                        newBook = book;
                        SelectedBookId.setText(book.getId());
                        SelectedBookTitle.setText(book.getTitle());
                        SelectedBookAuthors.setText(String.join(", ", book.getAuthors()));
                        SelectedBookPublisher.setText(book.getPublisher());
                        SelectedBookPublishedDate.setText(book.getPublishedDate());
                        SelectedBookDescription.setText(book.getDescription());
                        SelectedBookCategories.setText(String.join(", ", book.getCategories()));
                        SelectedBookIsbn13.setText(book.getISBN_13());
                        SelectedBookIsbn10.setText(book.getISBN_10());
                        SelectedBookReadLink.setText(book.getWebReaderLink());
                        SelectedBookCover.setImage(book.getImagePreview());
                    });
        }
    }

    public void AddNewBookButtonClicked() {
        if (newBook.getTitle() == null) {
            return;
        }
        try {
            MySql.addNewBook(newBook);
        } catch (SQLException _) {
            System.out.println("Error while adding books");
            System.out.println(newBook.toString());
        }
    }


    public void ReaderLinkClicked() {
        if (SelectedBookReadLink.getText().isEmpty()) return;
        try {
            Desktop.getDesktop().browse(new URI(SelectedBookReadLink.getText()));
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
