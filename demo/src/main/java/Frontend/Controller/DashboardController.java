package Frontend.Controller;

import Frontend.Class.Book;
import Frontend.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardController {
    @FXML
    private ListView searchList;
    @FXML
    private TextField SearchBar;
    @FXML
    private ImageView searchIcon;

    private boolean detectKeys(KeyEvent event) {
        return event.getCode().isKeypadKey() || event.getCode().isLetterKey() || event.getCode().isDigitKey() ||
               event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE;
    }

    private void updateSearchBar(String s) throws SQLException{
        String SQL = "SELECT name, author FROM books WHERE CONCAT(name, '  -  ', author) LIKE '%"
                + s + "%'";
        PreparedStatement stmt = Main.connection.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();
        searchList.getItems().clear();
        while (rs.next()) {
            Book book = new Book(rs.getString(1), rs.getString(2));
            String str = book.getName() + "  -  " + book.getAuthor();
            searchList.getItems().add(str);
        }
    }

    @FXML
    private void handleSearchIcon(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() > 0) {
//            Search
        }
    }


    @FXML
    private void handleSearchBar(KeyEvent keyEvent) {
        if (detectKeys(keyEvent)) {
            try {
                updateSearchBar(SearchBar.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void handleMouse(MouseEvent mouseEvent) {
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
                if (event.getButton() == MouseButton.PRIMARY && (!cell.isEmpty())) {
                    String item = cell.getItem();
                    SearchBar.setText(item);
                    try {
                        updateSearchBar(item);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return cell;
        });
    }
}
