package project.libraryclient.Controllers.DashBoard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import project.libraryclient.Class.Book;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Controllers.Card.Card_235_450_Controller;
import project.libraryclient.Database.MySql;

import java.io.IOException;
import java.net.URL;

import java.sql.SQLException;
import java.util.*;


public class DashBoardController implements Initializable {


    // ---------- Content display pane ---------- //
    public BorderPane ContentDisplay;


    // --------------- Exit button -------------- //
    public ImageView ExitButton;
    public TextField searchBox;
    public void ExitButtonMouseClicked() {
        System.exit(0); // close program
    }

    public void ExitButtonMouseEntered() {
        ExitButton.setImage(DATA.EXIT_WHITE_ICON);
    }

    public void ExitButtonMouseExited() {
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

        LoadHomePage();
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

        LoadNotificationPage();
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

        LoadDiscoverPage();
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

        LoadMyBookPage();
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

        LoadSettingPage();
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

    public void VersionInformationIconMouseEntered() {
    }

    public void VersionInformationIconMouseExited() {
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

    // Detect if a key is valid
    private boolean detectKeys(KeyEvent event) {
        return event.getCode().isKeypadKey() || event.getCode().isLetterKey() || event.getCode().isDigitKey() ||
                event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE;
    }
    
    private void LoadHomePage() {
        try {
            ScrollPane pane = FXMLLoader.load(
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

    private void LoadDiscoverPage() {
        try {
            ScrollPane pane = FXMLLoader.load(
                    Objects.requireNonNull(
                            getClass().getResource(DATA.DISCOVER_PAGE_LINK)));
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

    public ArrayList<AnchorPane> getListSearch() {
        ArrayList<AnchorPane> returnList = new ArrayList<>();
        ArrayList<String> bookNameList;
        try {
            bookNameList = MySql.getBookBySubstring(searchBox.getText());
            for (String name : bookNameList) {
                try {
                    ArrayList<Book> bookBasicInfos = MySql.getBasicInfoOfBook(name);
                    for (Book book : bookBasicInfos) {
                        if (returnList.size() >= 20) {
                            break;
                        }
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.CARD_235_450));
                            AnchorPane card = loader.load();
                            Card_235_450_Controller controller = loader.getController();
                            controller.setBookName(book.getTitle());
                            controller.setAuthorName(book.getAuthors());
                            controller.setBookCover(book.getImagePreview());
                            returnList.add(card);
//                             Add listener to a book
                            card.setOnMouseClicked(_ -> {
                                System.out.println(book.getId());
                            });
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Error while searching books");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return returnList;
    }

    public void SearchFieldOnAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.SEARCHPAGE_LINK));
            ScrollPane pane = loader.load();
            SearchController controller = loader.getController();
            controller.setContent(getListSearch());
            ContentDisplay.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}