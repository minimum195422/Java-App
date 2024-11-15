package Frontend.Library;

import Frontend.Class.Book;
import Frontend.Controller.BookCellController;
import Frontend.Main;
import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchBooks implements SearchBar {
    private TextField searchBarText;
    private ImageView searchIcon;
    private ListView<String> searchList;
    private Text resultText;
    private ListView<Book> searchResult = new ListView<>(FXCollections.observableArrayList());
    static boolean newText = true;
    public TextField getSearchBarText() {
        return searchBarText;
    }

    public void setSearchBarText(TextField searchBarText) {
        this.searchBarText = searchBarText;
    }

    public ImageView getSearchIcon() {
        return searchIcon;
    }

    public void setSearchIcon(ImageView searchIcon) {
        this.searchIcon = searchIcon;
    }

    public ListView<String> getSearchList() {
        return searchList;
    }

    public void setSearchList(ListView<String> searchList) {
        this.searchList = searchList;
    }

    public Text getResultText() {
        return resultText;
    }

    public void setResultText(Text resultText) {
        this.resultText = resultText;
    }

    public ListView<Book> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(ListView<Book> searchResult) {
        this.searchResult = searchResult;
    }

    public SearchBooks() {

    }

    public SearchBooks(TextField searchBarText, ImageView searchIcon, ListView<String> searchList, Text resultText, ListView<Book> searchResult) {
        setSearchBarText(searchBarText);
        setSearchIcon(searchIcon);
        setSearchList(searchList);
        setResultText(resultText);
        setSearchResult(searchResult);
    }

    private boolean detectKeys(KeyEvent event) {
        return event.getCode().isKeypadKey() || event.getCode().isLetterKey() || event.getCode().isDigitKey() ||
                event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE;
    }

    private void isSearchDone(boolean state) {
        if (!state) {
            searchList.setVisible(true);
            resultText.setVisible(false);
            searchResult.setVisible(false);
        } else {
            searchList.setVisible(false);
            resultText.setVisible(true);
            searchResult.setVisible(true);
            searchResult.getItems().clear();
        }
    }

    public void updateSearchBar(String s) throws SQLException {
        String SQL = "SELECT title, author FROM books WHERE CONCAT(title, '  -  ', author) LIKE '%"
                + s + "%'";
        PreparedStatement stmt = Main.connection.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();
        searchList.getItems().clear();
        while (rs.next()) {
            Book book = new Book(rs.getString(1), rs.getString(2));
            String str = book.getTitle() + "  -  " + book.getAuthor();
            searchList.getItems().add(str);
        }
    }

    public void handleSearchBar(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            try {
                updateSearchResult();
            } catch (SQLException _) {

            }
            return;
        }
        if (detectKeys(keyEvent)) {
            isSearchDone(false);
            try {
                newText = true;
                updateSearchBar(searchBarText.getText());
                searchList.setPrefHeight(30 * searchList.getItems().size());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void handleSearchIcon(MouseEvent mouseEvent) {
        if (!newText) {
            return;
        }
        if (mouseEvent.getClickCount() > 0) {
            try {
                newText = false;
                updateSearchResult();
            } catch (SQLException _) {

            }
        }
    }

    public void handleMouse(MouseEvent mouseEvent) {
        searchList.setCellFactory((unusedVariable) -> {
            ListCell<String> cell = new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                if ((event.getButton() == MouseButton.PRIMARY) && (!cell.isEmpty())) {
                    String item = cell.getItem();
                    searchBarText.setText(item);
                    try {
                        newText = true;
                        updateSearchBar(item);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return cell;
        });
    }

    public void updateSearchResult() throws SQLException {
        isSearchDone(true);
        searchResult.setCellFactory((unusedVariable) -> {
            return new ListCell<Book>() {
                @Override
                protected void updateItem(Book item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        BookCellController controller = new BookCellController();
                        controller.setBookInfo(item);
                        GridPane cell = controller.getBookInfo();
                        setGraphic(cell);
//                        setText(item.getTitle());
//                        setGraphic(new ImageView(item.getImagePreview()));
                    }
                }
            };
        });
        String SQL = "SELECT imagePreview, title, author, publishedDate, ISBN_13, price FROM books WHERE CONCAT(title, '  -  ', author) LIKE '%"
                + searchBarText.getText() + "%'";
        PreparedStatement stmt = Main.connection.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Book book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
            searchResult.getItems().add(book);
        }
        if (searchResult.getItems().isEmpty()) {
            resultText.setText("No results found");
        } else {
            resultText.setText("Results");
        }
        searchResult.setPrefHeight((BookCellController.HEIGHT + 1) * searchResult.getItems().size());
    }
}
