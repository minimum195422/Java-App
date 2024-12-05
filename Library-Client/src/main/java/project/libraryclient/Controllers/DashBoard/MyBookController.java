package project.libraryclient.Controllers.DashBoard;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import project.libraryclient.Book.Book;
import project.libraryclient.Client.Client;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Database.MySql;
import project.libraryclient.Models.JsonFileHandler;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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
        try {
            LoadDisplayBookList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void LoadBookList() throws IOException {
        JSONArray jsonArray = JsonFileHandler.getInstance().readJsonArray();
        if (!myListBook.isEmpty()) myListBook.clear();

        for (int i = 0; i < jsonArray.length(); ++i) {
            if (jsonArray.getJSONObject(i).getInt("user_id") == Client.getInstance().getUserId()) {
                myListBook.add(MySql.getInstance().QueryForBookCardById(
                        jsonArray.getJSONObject(i).getString("book_id")));
            }
        }
    }


    public void LoadDisplayBookList() throws IOException {
        LoadBookList();

        LoadBookList();
        if (myListBook.isEmpty()) {
            Label label = new Label("You haven't borrowed any book");
            label.setStyle("-fx-font-size: 20px;");
            MainVbox.getChildren().add(label);
            return;
        }


        if (!MainVbox.getChildren().isEmpty()) MainVbox.getChildren().clear();

        Platform.runLater(() -> {
            int row = (myListBook.size() + 4) / 5;
            List<HBox> hboxList = new ArrayList<>();
            for (int i = 0; i < row; ++i) {
                HBox hbox = new HBox();
                hbox.setSpacing(20);
                for (int j = 0; j < 5; ++j) {
                    if (i * 5 + j >= myListBook.size()) break;
                    hbox.getChildren().add(myListBook.get(i * 5 + j).getBookCard());
                    Book book = MySql.getInstance().GetBookById(myListBook.get(i * 5 + j).getId());
                    myListBook.get(i * 5 + j).getBookCard().setOnMouseClicked(_ -> {
                        HiddenPane.setVisible(true);
                        HiddenPane.setDisable(false);
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.PREVIEW_BOOK_LINK));
                            AnchorPane pane = loader.load();
                            BookPreviewController controller = loader.getController();

                            controller.setInfo(book);
                            HiddenPane.setCenter(pane);
                        } catch (IOException e) {
                            e.printStackTrace(System.out);
                        }
                    });
                }
                hboxList.add(hbox);
            }
            MainVbox.getChildren().setAll(hboxList);
        });
    }
}
