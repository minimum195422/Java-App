package project.libraryclient.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import project.libraryclient.Consts.IconsData;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {
    // Home button
    @FXML
    public Button HomeButton;
    @FXML
    public Text HomeButtonText;
    @FXML
    public ImageView HomeButtonIcon;


    // Favorite button
    @FXML
    public Button FavoriteButton;
    @FXML
    public ImageView FavoriteButtonIcon;
    @FXML
    public Text FavoriteButtonText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDefault();
    }

    public void setDefault() {
        HomeButtonIcon.setImage(IconsData.HOME_GREEN_ICON);
        HomeButtonText.setStyle("-fx-fill: #2fda73;");
    }

    public void HomeButtonClicked() {
        // Home button
        HomeButtonIcon.setImage(IconsData.HOME_GREEN_ICON);
        HomeButtonText.setStyle("-fx-fill: #2fda73;");

        // Favorite button
        FavoriteButtonIcon.setImage(IconsData.HEART_WHITE_ICON);
        FavoriteButtonText.setStyle("-fx-fill: #ffffff;");
    }

    public void FavoriteButtonClicked() {
        // Home button
        HomeButtonIcon.setImage(IconsData.HOME_WHITE_ICON);
        HomeButtonText.setStyle("-fx-fill: #ffffff;");

        // Favorite button
        FavoriteButtonIcon.setImage(IconsData.HEART_GREEN_ICON);
        FavoriteButtonText.setStyle("-fx-fill: #2fda73;");
    }
}
