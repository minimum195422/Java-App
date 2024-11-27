package project.libraryserver.Controllers.DashBoard.Content;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import project.libraryserver.API.GoogleBookAPI.BookAPI;
import project.libraryserver.Book.Book;
import project.libraryserver.Consts.SearchType;
import project.libraryserver.Database.MySql;

import java.io.IOException;
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


    public void SearchButtonClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        }
    }
}
