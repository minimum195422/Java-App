package project.libraryclient.Controllers.DashBoard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Controllers.Card.Card_235_450_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public VBox MainVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i < 3; ++i) {
            HBox list = new HBox();
            list.setSpacing(25);
            for (int j = 0; j < 5; ++j) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.CARD_235_450));
                    AnchorPane card = loader.load();
                    Card_235_450_Controller controller = loader.getController();

                    controller.setBookCover(loadImage("/project/libraryclient/Images/Icons/settinggray.png"));
                    controller.setAuthorName("author name");
                    controller.setBookName("book name");
                    list.getChildren().add(card);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            MainVBox.getChildren().add(list);
        }
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.CARD_235_450));
//            AnchorPane card = loader.load();
//            BookCard_235_450 controller = loader.getController();
//
//            controller.setBookCover(loadImage("/project/libraryclient/Images/Icons/settinggray.png"));
//
//            MainVBox.getChildren().add(card);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    private Image loadImage(String path) {
        return new Image(Objects.requireNonNull(HomeController.class.getResourceAsStream(path)));
    }
}
