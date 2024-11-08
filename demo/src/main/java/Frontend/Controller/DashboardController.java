package Frontend.Controller;

import Frontend.Class.Book;
import Frontend.Library.SearchBar;
import Frontend.Library.SearchBooks;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class DashboardController {
    @FXML
    private TextField searchBarText;
    @FXML
    private ImageView searchIcon;
    @FXML
    private ListView<String> searchList;
    @FXML
    private Text resultText;
    @FXML
    private ListView<Book> searchResult = new ListView<Book>(FXCollections.observableArrayList());

    SearchBooks searchBar = new SearchBooks();

    void updateData() {
        searchBar = new SearchBooks(searchBarText, searchIcon, searchList, resultText, searchResult);
    }

    @FXML
    private void handleSearchBar(KeyEvent keyEvent) {
//        System.out.println("\ud835\udc82");
        updateData();
        searchBar.handleSearchBar(keyEvent);
    }

    @FXML
    private void handleSearchIcon(MouseEvent mouseEvent) {
        updateData();
        searchBar.handleSearchIcon(mouseEvent);
    }

    @FXML
    private void handleMouse(MouseEvent mouseEvent) {
        updateData();
        searchBar.handleMouse(mouseEvent);
    }
}
