package project.libraryclient.Controllers.DashBoard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public VBox MainVBox;

    @FXML
    public MediaView MediaContent1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        String videoUri = getClass().getResource("/project/libraryclient/Videos/Benefits_Of_Reading.mp4").toExternalForm();
//        MediaPlayer mediaPlayer = new MediaPlayer(new Media(videoUri));
//        MediaContent1.setMediaPlayer(mediaPlayer);
//        mediaPlayer.setAutoPlay(true);

//        for (int i = 0; i < 3; ++i) {
//            HBox list = new HBox();
//            list.setSpacing(25);
//            for (int j = 0; j < 5; ++j) {
//                try {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.CARD_235_450));
//                    AnchorPane card = loader.load();
//                    Card_235_450_Controller controller = loader.getController();
//
//                    controller.setBookCover(loadImage("/project/libraryclient/Images/Icons/settinggray.png"));
//                    controller.setAuthorName("author name");
//                    controller.setBookName("book name");
//                    list.getChildren().add(card);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//            MainVBox.getChildren().add(list);
//        }

    }

//    private Image loadImage(String path) {
//        return new Image(Objects.requireNonNull(HomeController.class.getResourceAsStream(path)));
//    }

}
