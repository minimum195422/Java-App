package project.libraryclient.Controllers.DashBoard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import project.libraryclient.Book.Book;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Database.MySql;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    public HBox DisplayRecentlyAddBook;

    @FXML
    public BorderPane HiddenPane;

    public ArrayList<Book> RecentlyAddBookList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HiddenPane.setVisible(false);
        HiddenPane.setDisable(true);
        LoadBookList();
    }

    public void LoadBookList() {
        RecentlyAddBookList = MySql.getInstance().QueryForBookCard();
        for (Book book : RecentlyAddBookList) {
            DisplayRecentlyAddBook.getChildren().add(
                    book.getBookCard()
            );

            book.getBookCard().setOnMouseClicked(_ -> {
                HiddenPane.setVisible(true);
                HiddenPane.setDisable(false);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.PREVIEW_BOOK_LINK));
                    AnchorPane pane = loader.load();
                    BookPreviewController controller = loader.getController();
                    controller.setInfor(book);
                    HiddenPane.setCenter(pane);
                } catch (IOException e) {
                    e.printStackTrace(System.out);
                }
            });
        }
    }
}
