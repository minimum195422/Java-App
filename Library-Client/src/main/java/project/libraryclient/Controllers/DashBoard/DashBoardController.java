package project.libraryclient.Controllers.DashBoard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Database.MySql;

import java.io.IOException;
import java.net.URL;

import java.util.Objects;
import java.util.ResourceBundle;


public class DashBoardController implements Initializable {


    // ---------- Content display pane ---------- //
    @FXML
    public BorderPane ContentDisplay;

    @FXML
    public Text DisplayUserName, DisplayUserMail;


    // --------------- Search Box -------------- //
    @FXML
    public TextField searchBox;

    @FXML
    public Button SearchButton;


    // ---------- Toggle Button Group ---------- //
    ToggleGroup function_button_toggle_group = new ToggleGroup();


    // --------------- Exit button -------------- //
    @FXML
    public ToggleButton ExitButton;

    @FXML
    public ImageView ExitButtonIcon;

    @FXML
    public Text ExitButtonText;


    public void ExitButtonMouseClicked() {
        System.exit(0); // close program
    }

    public void ExitButtonMouseEntered() {
        if (!ExitButton.isSelected()) {
            ExitButtonIcon.setImage(DATA.EXIT_BLACK_ICON);
            ExitButtonText.setStyle("-fx-fill: #000000;");
        }
    }

    public void ExitButtonMouseExited() {
        if (!ExitButton.isSelected()) {
            ExitButtonIcon.setImage(DATA.EXIT_GRAY_ICON);
            ExitButtonText.setStyle("-fx-fill: #adb5bd;");
        }
    }




    // ---------- Home Button ---------- //
    @FXML
    public ToggleButton HomeButton;
    @FXML
    public ImageView HomeButtonIcon;
    @FXML
    public Text HomeButtonText;

    public void HomeButtonMouseClicked() {

        // Home Button
        HomeButtonIcon.setImage(DATA.HOME_BLACK_ICON);
        HomeButtonText.setStyle("-fx-fill: #000000;");

        // Notification Button
        NotificationButtonIcon.setImage(DATA.NOTIFICATION_GRAY_ICON);
        NotificationButtonText.setStyle("-fx-fill: #adb5bd;");

        // MyBook Button
        MyBookButtonIcon.setImage(DATA.MYBOOK_GRAY_ICON);
        MyBookButtonText.setStyle("-fx-fill: #adb5bd;");

        // Setting Button
        SettingButtonIcon.setImage(DATA.SETTING_GRAY_ICON);
        SettingButtonText.setStyle("-fx-fill: #adb5bd;");

        LoadHomePage();
    }

    public void HomeButtonMouseEntered() {
        if (!HomeButton.isSelected()) {
            HomeButtonIcon.setImage(DATA.HOME_BLACK_ICON);
            HomeButtonText.setStyle("-fx-fill: #000000;");
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
        NotificationButtonIcon.setImage(DATA.NOTIFICATION_BLACK_ICON);
        NotificationButtonText.setStyle("-fx-fill: #000000;");

        // MyBook Button
        MyBookButtonIcon.setImage(DATA.MYBOOK_GRAY_ICON);
        MyBookButtonText.setStyle("-fx-fill: #adb5bd;");

        // Setting Button
        SettingButtonIcon.setImage(DATA.SETTING_GRAY_ICON);
        SettingButtonText.setStyle("-fx-fill: #adb5bd;");

        LoadNotificationPage();
    }

    public void NotificationButtonMouseEntered() {
        if (!NotificationButton.isSelected()) {
            NotificationButtonIcon.setImage(DATA.NOTIFICATION_BLACK_ICON);
            NotificationButtonText.setStyle("-fx-fill: #000000;");
        }
    }

    public void NotificationButtonMouseExited() {
        if (!NotificationButton.isSelected()) {
            NotificationButtonIcon.setImage(DATA.NOTIFICATION_GRAY_ICON);
            NotificationButtonText.setStyle("-fx-fill: #adb5bd;");
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

        // MyBook Button
        MyBookButtonIcon.setImage(DATA.MYBOOK_BLACK_ICON);
        MyBookButtonText.setStyle("-fx-fill: #000000;");

        // Setting Button
        SettingButtonIcon.setImage(DATA.SETTING_GRAY_ICON);
        SettingButtonText.setStyle("-fx-fill: #adb5bd;");

        LoadMyBookPage();
    }

    public void MyBookButtonMouseEntered() {
        if (!MyBookButton.isSelected()) {
            MyBookButtonIcon.setImage(DATA.MYBOOK_BLACK_ICON);
            MyBookButtonText.setStyle("-fx-fill: #000000;");
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

        // MyBook Button
        MyBookButtonIcon.setImage(DATA.MYBOOK_GRAY_ICON);
        MyBookButtonText.setStyle("-fx-fill: #adb5bd;");

        // Setting Button
        SettingButtonIcon.setImage(DATA.SETTING_BLACK_ICON);
        SettingButtonText.setStyle("-fx-fill: #000000;");

        LoadSettingPage();
    }

    public void SettingButtonMouseEntered() {
        if (!SettingButton.isSelected()) {
            SettingButtonIcon.setImage(DATA.SETTING_BLACK_ICON);
            SettingButtonText.setStyle("-fx-fill: #000000;");
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


    // -------------------- initialize -------------------- //
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        try {
//            Client.getInstance().addListener((_, name, mail) ->
//                Platform.runLater(() -> {
//                    DisplayUserName.setText(name);
//                    DisplayUserMail.setText(mail);
//            }));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        setDefault();
    }

    public void setDefault() {

        // ToggleGroup
        HomeButton.setToggleGroup(function_button_toggle_group);
        NotificationButton.setToggleGroup(function_button_toggle_group);
        MyBookButton.setToggleGroup(function_button_toggle_group);
        SettingButton.setToggleGroup(function_button_toggle_group);
        ExitButton.setToggleGroup(function_button_toggle_group);

        // Set button group always have one in used
        function_button_toggle_group.selectedToggleProperty().addListener(
                (_, oldToggle, newToggle) -> {
                    if (newToggle == null) {
                        function_button_toggle_group.selectToggle(oldToggle);
                    }
                });
        // Tooltip
        Tooltip version = new Tooltip(DATA.APP_VERSION);
        Tooltip.install(VersionInformationIcon, version);
        version.setShowDelay(new Duration(200));
        version.setHideDelay(new Duration(200));

        HomeButton.setSelected(true);
        HomeButtonMouseClicked();
    }


    private void LoadHomePage() {
        try {
            AnchorPane pane = FXMLLoader.load(
                    Objects.requireNonNull(
                            getClass().getResource(DATA.HOMEPAGE_LINK)));
            ContentDisplay.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private void LoadNotificationPage() {
        try {
            ScrollPane pane = FXMLLoader.load(
                    Objects.requireNonNull(
                            getClass().getResource(DATA.NOTIFICATION_PAGE_LINK)));
            ContentDisplay.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private void LoadSettingPage() {
        try {
            ScrollPane pane = FXMLLoader.load(
                    Objects.requireNonNull(
                            getClass().getResource(DATA.SETTING_PAGE_LINK)));
            ContentDisplay.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private void LoadMyBookPage() {
        try {
            ScrollPane pane = FXMLLoader.load(
                    Objects.requireNonNull(
                            getClass().getResource(DATA.MYBOOK_PAGE_LINK)));
            ContentDisplay.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public void SearchAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.SEARCHPAGE_LINK));
            AnchorPane pane = loader.load();
            SearchController controller = loader.getController();
            controller.setContent(MySql.getInstance().GetSearchBookList(searchBox.getText()));
            ContentDisplay.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

}