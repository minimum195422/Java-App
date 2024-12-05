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
import project.libraryclient.Book.Book;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Database.MySql;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    public BorderPane HiddenPane;

    public ArrayList<Book> RecentlyAddBookList = new ArrayList<>();

    @FXML
    public VBox MainVbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HiddenPane.setVisible(false);
        HiddenPane.setDisable(true);
        LoadBookList();
    }

    public void LoadBookList() {
        Label label = new Label("New book");
        label.setStyle("-fx-font-size: 20px; -fx-padding: 10px 15px 20px 25px;");
        MainVbox.getChildren().add(label);
        RecentlyAddBookList = MySql.getInstance().QueryForBookCard();

        new Thread(() -> {
            int row = (RecentlyAddBookList.size() + 4) / 5;

            Platform.runLater(() -> {
                for (int i = 0; i < row; ++i) {
                    HBox hbox = new HBox();
                    hbox.setSpacing(20);
                    hbox.setPadding(new Insets(0, 0, 20, 10));
                    for (int j = 0; j < 5; ++j) {
                        if (i * 5 + j >= RecentlyAddBookList.size()) break;
                        hbox.getChildren().add(RecentlyAddBookList.get(i * 5 + j).getBookCard());
                        Book book = MySql.getInstance().GetBookById(RecentlyAddBookList.get(i * 5 + j).getId());
                        RecentlyAddBookList.get(i * 5 + j).getBookCard().setOnMouseClicked(_ -> {
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
                    MainVbox.getChildren().add(hbox);
                }
            });
        }).start();
    }
}
