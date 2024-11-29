package project.libraryclient.Controllers.DashBoard;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
                vbox.getChildren().add(list.get(i * 5 + j));
            }
            MainVBox.getChildren().add(vbox);
        }
    }
}
