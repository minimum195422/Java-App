package project.libraryclient.Controllers.DashBoard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import project.libraryclient.Book.Book;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Database.MySql;
import project.libraryclient.Models.JsonFileHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyBookController implements Initializable {

    @FXML
    public BorderPane HiddenPane;

    @FXML
    public VBox MainVbox;

    ArrayList<Book> myListBook = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HiddenPane.setVisible(false);
        HiddenPane.setDisable(true);
        LoadDisplayBookList();
    }

    private void LoadBookList() {
        JSONArray jsonArray = JsonFileHandler.getInstance().readJsonArray();

        for (int i = 0; i < jsonArray.length(); ++i) {
            myListBook.add(MySql.getInstance().QueryForBookCardById(
                    jsonArray.getJSONObject(i).getString("book_id")));
        }
    }

    public void LoadDisplayBookList() {
        LoadBookList();

        if (myListBook.isEmpty()) {
            Label label = new Label("You haven't borrowed any book");
            label.setStyle("-fx-font-size: 20px;");
            MainVbox.getChildren().add(label);
            return;
        }

        if (!MainVbox.getChildren().isEmpty()) MainVbox.getChildren().clear();

        for (Book book : myListBook) {
            MainVbox.getChildren().add(book.getBookCard());

            book.getBookCard().setOnMouseClicked(_ -> {
                HiddenPane.setVisible(true);
                HiddenPane.setDisable(false);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.PREVIEW_BOOK_LINK));
                    AnchorPane pane = loader.load();
                    BookPreviewController controller = loader.getController();
                    Book fullBook = MySql.getInstance().GetBookById(book.getId());
                    controller.setInfo(fullBook);
                    HiddenPane.setCenter(pane);
                } catch (IOException e) {
                    e.printStackTrace(System.out);
                }
            });
        }
    }
}
