package Frontend.Controller;

import Frontend.Class.Book;
import Frontend.Library.SceneHandler;
import Frontend.Library.SearchBooks;
import Frontend.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class DashboardController {
    @FXML
    private Button addButton;
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

    private void resetAll() {
        searchBarText.clear();
        searchList.getItems().clear();
        searchResult.getItems().clear();
        searchResult.setVisible(false);
        resultText.setVisible(false);
    }

    public void initialize() {
        searchBar = new SearchBooks(searchBarText, searchIcon, searchList, resultText, searchResult);
    }

    @FXML
    private void handleSearchBar(KeyEvent keyEvent) {
//        System.out.println("\ud835\udc82");
        searchBar.handleSearchBar(keyEvent);
    }

    @FXML
    private void handleSearchIcon(MouseEvent mouseEvent) {
        searchBar.handleSearchIcon(mouseEvent);
    }

    @FXML
    private void handleMouse(MouseEvent mouseEvent) {
        searchBar.handleMouse(mouseEvent);
    }

    @FXML
    private void redirectToAddBooks(ActionEvent actionEvent) {
        SceneHandler.getInstance(Main.class, null).setScene("AddBooksPage");
        resetAll();
    }
}
