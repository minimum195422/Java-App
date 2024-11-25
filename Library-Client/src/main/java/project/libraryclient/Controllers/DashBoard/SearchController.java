package project.libraryclient.Controllers.DashBoard;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import project.libraryclient.Class.Book;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Controllers.Card.Card_235_450_Controller;

import java.io.IOException;
import java.util.List;

public class SearchController {
    public VBox MainVBox;

    public void setContent(List<AnchorPane> list) {
        int row = (list.size() + 4) / 5;
        for (int i = 0; i < row; ++i) {
            HBox vbox = new HBox();
            vbox.setSpacing(25);
            for (int j = 0; j < 5; ++j) {
                if (i * 5 + j >= list.size()) break;
                AnchorPane card = list.get(i * 5 + j);
                vbox.getChildren().add(card);
            }
            MainVBox.getChildren().add(vbox);
        }
    }
}
