package project.libraryclient.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import project.libraryclient.Consts.DashBoardData;
import project.libraryclient.Consts.IconsData;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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

    // main border pane
    @FXML
    public BorderPane MainBorderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDefault();
    }

    public void setDefault() {
        HomeButtonIcon.setImage(IconsData.HOME_GREEN_ICON);
        HomeButtonText.setStyle("-fx-fill: #2fda73;");

        LoadHomePage();
    }

    private void LoadHomePage() {
        try {
            ScrollPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(DashBoardData.DASHBOARD_HOMEPAGE_LINK)));
            MainBorderPane.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
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
