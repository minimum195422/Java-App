package project.libraryclient.Controllers.DashBoard;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.util.ArrayList;
import java.util.List;

public class SearchController {

    @FXML
    public BorderPane HiddenPane;

    @FXML
    public VBox MainVbox;


    public void setContent(ArrayList<Book> SearchList) {
        HiddenPane.setVisible(false);
        HiddenPane.setDisable(true);

        if (!MainVbox.getChildren().isEmpty()) MainVbox.getChildren().clear();

        if (SearchList.isEmpty()) {
            Label label = new Label("Can't found any book, please try with another prompt");
            label.setStyle("-fx-font-size: 24px;");
            MainVbox.getChildren().add(label);
            return;
        }
        new Thread(() -> {
            int row = (SearchList.size() + 3) / 4;

            Platform.runLater(() -> {
                List<HBox> hboxList = new ArrayList<>();
                for (int i = 0; i < row; ++i) {
                    HBox hbox = new HBox();
                    hbox.setSpacing(75);
                    hbox.setPadding(new Insets(0, 0, 0, 30));
                    for (int j = 0; j < 4; ++j) {
                        if (i * 4 + j >= SearchList.size()) break;
                        hbox.getChildren().add(SearchList.get(i * 4 + j).getBookCard());
                        Book book = MySql.getInstance().GetBookById(SearchList.get(i * 4 + j).getId());
                        SearchList.get(i * 4 + j).getBookCard().setOnMouseClicked(_ -> {
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
        }).start();
    }
}