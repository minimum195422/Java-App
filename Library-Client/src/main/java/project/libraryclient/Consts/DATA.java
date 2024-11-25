package project.libraryclient.Consts;

import javafx.scene.image.Image;

import java.util.Objects;

public class DATA {

// ---------------------------------------------------------------- //
// -------------------- Dashboard content link -------------------- //
// ---------------------------------------------------------------- //

    // Application version
    public static final String APP_VERSION = "Library Client Version 1.0.0";

    // Home button items
    public static final Image HOME_WHITE_ICON = loadImage("/project/libraryclient/Images/Icons/homewhite.png");
    public static final Image HOME_GRAY_ICON = loadImage("/project/libraryclient/Images/Icons/home_gray.png");

    // Notification button items
    public static final Image NOTIFICATION_WHITE_ICON = loadImage("/project/libraryclient/Images/Icons/notification-bell-white.png");
    public static final Image NOTIFICATION_GRAY_ICON = loadImage("/project/libraryclient/Images/Icons/notification-bell-gray.png");

    // Discover button items
    public static final Image DISCOVER_WHITE_ICON = loadImage("/project/libraryclient/Images/Icons/compasswhite.png");
    public static final Image DISCOVER_GRAY_ICON = loadImage("/project/libraryclient/Images/Icons/compassgray.png");

    // Mybook button items
    public static final Image MYBOOK_WHITE_ICON = loadImage("/project/libraryclient/Images/Icons/librarywhite.png");
    public static final Image MYBOOK_GRAY_ICON = loadImage("/project/libraryclient/Images/Icons/librarygray.png");

    // Setting button items
    public static final Image SETTING_WHITE_ICON = loadImage("/project/libraryclient/Images/Icons/settingwhite.png");;
    public static final Image SETTING_GRAY_ICON = loadImage("/project/libraryclient/Images/Icons/settinggray.png");;

    // Exit button items
    public static final Image EXIT_WHITE_ICON = loadImage("/project/libraryclient/Images/Icons/x-button_white.png");
    public static final Image EXIT_GRAY_ICON = loadImage("/project/libraryclient/Images/Icons/x-button.png");

    // Home page link 
    public static final String HOMEPAGE_LINK = "/project/libraryclient/FXML/ContentPage/Home.fxml";
    public static final String SEARCHPAGE_LINK = "/project/libraryclient/FXML/ContentPage/Search.fxml";
    // Discover page link
    public static final String DISCOVER_PAGE_LINK = "/project/libraryclient/FXML/ContentPage/Discover.fxml";

    // Setting page link
    public static final String SETTING_PAGE_LINK = "/project/libraryclient/FXML/ContentPage/Setting.fxml";

    // Notification page link
    public static final String NOTIFICATION_PAGE_LINK = "/project/libraryclient/FXML/ContentPage/Notification.fxml";

    // My Book page link
    public static final String MYBOOK_PAGE_LINK = "/project/libraryclient/FXML/ContentPage/MyBook.fxml";

// ---------------------------------------------------------------- //
// ------------------------- Card ui link ------------------------- //
// ---------------------------------------------------------------- //
    // Book card size 235 x 450
    public static final String CARD_235_450 = "/project/libraryclient/FXML/Card/BookCard_180_450.fxml";

// ---------------------------------------------------------------- //
// -------------------------- Scene name -------------------------- //
// ---------------------------------------------------------------- //
    public static final String SCENE_LOGIN_PAGE = "LoginController";
    public static final String SCENE_REGISTER_PAGE = "RegisterController";
    public static final String SCENE_BEING_DEVELOPMENT = "BeingDevelopment";
    public static final String SCENE_DASHBOARD = "DashBoardController";
    public static final String SCENE_VERIFY_PAGE = "VerifyController";

// ---------------------------------------------------------------- //
// --------------------- Load image from link --------------------- //
// ---------------------------------------------------------------- //
    private static Image loadImage(String path) {
        return new Image(Objects.requireNonNull(DATA.class.getResourceAsStream(path)));
    }

// ---------------------------------------------------------------- //
// -------------------------- JDBC data --------------------------- //
// ---------------------------------------------------------------- //
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/library";
    private static final String JDBC_USER = "minimum195422";
    private static final String JDBC_PASSWORD = "Humhayha12#";

    public static String GetJdbcUrl() { return JDBC_URL; }

    public static String GetJdbcUser() { return JDBC_USER; }

    public static String GetJdbcPassword() { return JDBC_PASSWORD; }

// ---------------------------------------------------------------- //
// ---------------------- Google API data ------------------------- //
// ---------------------------------------------------------------- //
    private static final String CLIENT_ID = "884269234343-jmlvf2n1d25frljrd2cd09tpria7pe84.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final String REDIRECT_LINK = "https://localhost:8888";
    private static final String SCOPE = "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email";

    public static String GetGoogleClientId() { return CLIENT_ID;}
    public static String GetGoogleClientSecret() { return CLIENT_SECRET;}
    public static String GetGoogleRedirectLink() { return REDIRECT_LINK;}
    public static String GetGoogleAPIScope() { return SCOPE;}
    
}
