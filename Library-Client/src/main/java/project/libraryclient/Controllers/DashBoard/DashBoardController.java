package project.libraryclient.Controllers.DashBoard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import project.libraryclient.App;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Models.SceneHandler;

import java.io.IOException;
import java.net.URL;

import java.util.Objects;
import java.util.ResourceBundle;


public class DashBoardController implements Initializable {


    // ---------- Content display pane ---------- //
    public BorderPane ContentDisplay;
    
    // Exit button
    public ImageView ExitButton;
    public void ExitButtonMouseClicked(MouseEvent event) {
        SceneHandler.getInstance(App.class, null).SetScene(DATA.SCENE_LOGIN_PAGE);
    }

    public void ExitButtonMouseEntered(MouseEvent event) {
        ExitButton.setImage(DATA.EXIT_WHITE_ICON);
    }

    public void ExitButtonMouseExited(MouseEvent event) {
        ExitButton.setImage(DATA.EXIT_GRAY_ICON);
    }
    
    // ---------- Toggle Button Group ---------- //
    ToggleGroup function_button_toggle_group = new ToggleGroup();

    // ---------- Home Button ---------- //
    @FXML
    public ToggleButton HomeButton;
    @FXML
    public ImageView HomeButtonIcon;
    @FXML
    public Text HomeButtonText;

    public void HomeButtonMouseClicked() {

        // Home Button
        HomeButtonIcon.setImage(DATA.HOME_WHITE_ICON);
        HomeButtonText.setStyle("-fx-fill: #ffffff;");

        // Notification Button
        NotificationButtonIcon.setImage(DATA.NOTIFICATION_GRAY_ICON);
        NotificationButtonText.setStyle("-fx-fill: #adb5bd;");

        // Discover Button
        DiscoverButtonIcon.setImage(DATA.DISCOVER_GRAY_ICON);
        DiscoverButtonText.setStyle("-fx-fill: #adb5bd;");

        // MyBook Button
        MyBookButtonIcon.setImage(DATA.MYBOOK_GRAY_ICON);
        MyBookButtonText.setStyle("-fx-fill: #adb5bd;");

        // Setting Button
        SettingButtonIcon.setImage(DATA.SETTING_GRAY_ICON);
        SettingButtonText.setStyle("-fx-fill: #adb5bd;");

    }

    public void HomeButtonMouseEntered() {
        if (!HomeButton.isSelected()) {
            HomeButtonIcon.setImage(DATA.HOME_WHITE_ICON);
            HomeButtonText.setStyle("-fx-fill: #ffffff;");
        }
    }

    public void HomeButtonMouseExited() {
        if (!HomeButton.isSelected()) {
            HomeButtonIcon.setImage(DATA.HOME_GRAY_ICON);
            HomeButtonText.setStyle("-fx-fill: #adb5bd;");
        }
    }

    // ---------- Notification Button ---------- //
    @FXML
    public ToggleButton NotificationButton;
    @FXML
    public ImageView NotificationButtonIcon;
    @FXML
    public Text NotificationButtonText;

    public void NotificationButtonMouseClicked() {

        // Home Button
        HomeButtonIcon.setImage(DATA.HOME_GRAY_ICON);
        HomeButtonText.setStyle("-fx-fill: #adb5bd;");

        // Notification Button
        NotificationButtonIcon.setImage(DATA.NOTIFICATION_WHITE_ICON);
        NotificationButtonText.setStyle("-fx-fill: #ffffff;");

        // Discover Button
        DiscoverButtonIcon.setImage(DATA.DISCOVER_GRAY_ICON);
        DiscoverButtonText.setStyle("-fx-fill: #adb5bd;");

        // MyBook Button
        MyBookButtonIcon.setImage(DATA.MYBOOK_GRAY_ICON);
        MyBookButtonText.setStyle("-fx-fill: #adb5bd;");

        // Setting Button
        SettingButtonIcon.setImage(DATA.SETTING_GRAY_ICON);
        SettingButtonText.setStyle("-fx-fill: #adb5bd;");

    }

    public void NotificationButtonMouseEntered() {
        if (!NotificationButton.isSelected()) {
            NotificationButtonIcon.setImage(DATA.NOTIFICATION_WHITE_ICON);
            NotificationButtonText.setStyle("-fx-fill: #ffffff;");
        }
    }

    public void NotificationButtonMouseExited() {
        if (!NotificationButton.isSelected()) {
            NotificationButtonIcon.setImage(DATA.NOTIFICATION_GRAY_ICON);
            NotificationButtonText.setStyle("-fx-fill: #adb5bd;");
        }
    }



    // ---------- Discover Button ---------- //
    @FXML
    public ToggleButton DiscoverButton;
    @FXML
    public ImageView DiscoverButtonIcon;
    @FXML
    public Text DiscoverButtonText;

    public void DiscoverButtonMouseClicked() {

        // Home Button
        HomeButtonIcon.setImage(DATA.HOME_GRAY_ICON);
        HomeButtonText.setStyle("-fx-fill: #adb5bd;");

        // Notification Button
        NotificationButtonIcon.setImage(DATA.NOTIFICATION_GRAY_ICON);
        NotificationButtonText.setStyle("-fx-fill: #adb5bd;");

        // Discover Button
        DiscoverButtonIcon.setImage(DATA.DISCOVER_WHITE_ICON);
        DiscoverButtonText.setStyle("-fx-fill: #ffffff;");

        // MyBook Button
        MyBookButtonIcon.setImage(DATA.MYBOOK_GRAY_ICON);
        MyBookButtonText.setStyle("-fx-fill: #adb5bd;");

        // Setting Button
        SettingButtonIcon.setImage(DATA.SETTING_GRAY_ICON);
        SettingButtonText.setStyle("-fx-fill: #adb5bd;");

    }

    public void DiscoverButtonMouseEntered() {
        if (!DiscoverButton.isSelected()) {
            DiscoverButtonIcon.setImage(DATA.DISCOVER_WHITE_ICON);
            DiscoverButtonText.setStyle("-fx-fill: #ffffff;");
        }
    }

    public void DiscoverButtonMouseExited() {
        if (!DiscoverButton.isSelected()) {
            DiscoverButtonIcon.setImage(DATA.DISCOVER_GRAY_ICON);
            DiscoverButtonText.setStyle("-fx-fill: #adb5bd;");
        }
    }

    // ---------- MyBook Button ---------- //
    @FXML
    public ToggleButton MyBookButton;
    @FXML
    public ImageView MyBookButtonIcon;
    @FXML
    public Text MyBookButtonText;

    public void MyBookButtonMouseClicked() {

        // Home Button
        HomeButtonIcon.setImage(DATA.HOME_GRAY_ICON);
        HomeButtonText.setStyle("-fx-fill: #adb5bd;");

        // Notification Button
        NotificationButtonIcon.setImage(DATA.NOTIFICATION_GRAY_ICON);
        NotificationButtonText.setStyle("-fx-fill: #adb5bd;");

        // Discover Button
        DiscoverButtonIcon.setImage(DATA.DISCOVER_GRAY_ICON);
        DiscoverButtonText.setStyle("-fx-fill: #adb5bd;");

        // MyBook Button
        MyBookButtonIcon.setImage(DATA.MYBOOK_WHITE_ICON);
        MyBookButtonText.setStyle("-fx-fill: #ffffff;");

        // Setting Button
        SettingButtonIcon.setImage(DATA.SETTING_GRAY_ICON);
        SettingButtonText.setStyle("-fx-fill: #adb5bd;");

    }

    public void MyBookButtonMouseEntered() {
        if (!MyBookButton.isSelected()) {
            MyBookButtonIcon.setImage(DATA.MYBOOK_WHITE_ICON);
            MyBookButtonText.setStyle("-fx-fill: #ffffff;");
        }
    }

    public void MyBookButtonMouseExited() {
        if (!MyBookButton.isSelected()) {
            MyBookButtonIcon.setImage(DATA.MYBOOK_GRAY_ICON);
            MyBookButtonText.setStyle("-fx-fill: #adb5bd;");
        }
    }

    // ---------- Setting Button ---------- //
    @FXML
    public ToggleButton SettingButton;
    @FXML
    public ImageView SettingButtonIcon;
    @FXML
    public Text SettingButtonText;

    public void SettingButtonMouseClicked() {

        // Home Button
        HomeButtonIcon.setImage(DATA.HOME_GRAY_ICON);
        HomeButtonText.setStyle("-fx-fill: #adb5bd;");

        // Notification Button
        NotificationButtonIcon.setImage(DATA.NOTIFICATION_GRAY_ICON);
        NotificationButtonText.setStyle("-fx-fill: #adb5bd;");

        // Discover Button
        DiscoverButtonIcon.setImage(DATA.DISCOVER_GRAY_ICON);
        DiscoverButtonText.setStyle("-fx-fill: #adb5bd;");

        // MyBook Button
        MyBookButtonIcon.setImage(DATA.MYBOOK_GRAY_ICON);
        MyBookButtonText.setStyle("-fx-fill: #adb5bd;");

        // Setting Button
        SettingButtonIcon.setImage(DATA.SETTING_WHITE_ICON);
        SettingButtonText.setStyle("-fx-fill: #ffffff;");

    }

    public void SettingButtonMouseEntered() {
        if (!SettingButton.isSelected()) {
            SettingButtonIcon.setImage(DATA.SETTING_WHITE_ICON);
            SettingButtonText.setStyle("-fx-fill: #ffffff;");
        }
    }

    public void SettingButtonMouseExited() {
        if (!SettingButton.isSelected()) {
            SettingButtonIcon.setImage(DATA.SETTING_GRAY_ICON);
            SettingButtonText.setStyle("-fx-fill: #adb5bd;");
        }
    }


    // ---------- VersionInformation Icon ---------- //

    public ImageView VersionInformationIcon;

    public void VersionInformationIconMouseEntered(MouseEvent event) {
    }

    public void VersionInformationIconMouseExited(MouseEvent event) {
    }


    // -------------------- initialize -------------------- //
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDefault();
    }

    public void setDefault() {

        // ToggleGroup
        HomeButton.setToggleGroup(function_button_toggle_group);
        NotificationButton.setToggleGroup(function_button_toggle_group);
        DiscoverButton.setToggleGroup(function_button_toggle_group);
        MyBookButton.setToggleGroup(function_button_toggle_group);
        SettingButton.setToggleGroup(function_button_toggle_group);

        // Tooltip
        Tooltip version = new Tooltip(DATA.APP_VERSION);
        Tooltip.install(VersionInformationIcon, version);
        version.setShowDelay(new Duration(200));
        version.setHideDelay(new Duration(200));

        HomeButton.setSelected(true);
        HomeButtonMouseClicked();

        // Set content
        LoadHomePage();
    }


    private void LoadHomePage() {
        try {
            ScrollPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(DATA.HOMEPAGE_LINK)));
            ContentDisplay.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }


}