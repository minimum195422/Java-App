package project.englishapp.Controllers.ContentPage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import project.englishapp.Consts.DashBoardData;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public VBox test_vbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 20; i++) {
            try {
                AnchorPane anchorPane = GetItemCenterType1(DashBoardData.DASHBOARD_ITEM_CENTER_TYPE_1);
                test_vbox.getChildren().add(anchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public AnchorPane GetItemCenterType1(String link) throws IOException {
        return FXMLLoader.load(Objects.requireNonNull(getClass().getResource(link)));
    }
}
