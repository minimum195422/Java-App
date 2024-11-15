package Frontend.Library;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public interface SearchBar {
    void handleSearchBar(KeyEvent keyEvent);
    void updateSearchBar(String s) throws SQLException;
    void handleSearchIcon(MouseEvent mouseEvent);
    void updateSearchResult() throws SQLException ;
    void handleMouse(MouseEvent mouseEvent);
}
