package project.libraryclient.Consts;

import javafx.scene.image.Image;

import java.net.URI;
import java.util.Objects;

public class DATA {

// ---------------------------------------------------------------- //
// -------------------- Dashboard content link -------------------- //
// ---------------------------------------------------------------- //

    // Application version
    public static final String APP_VERSION = "Library Client Version 1.0.0";

    // Icons
    public static final Image HOME_BLACK_ICON = loadImage("/project/libraryclient/Images/Icons/homeblack.png");
    public static final Image HOME_GRAY_ICON = loadImage("/project/libraryclient/Images/Icons/home_gray.png");
    public static final Image NOTIFICATION_BLACK_ICON = loadImage("/project/libraryclient/Images/Icons/notificationblack.png");
    public static final Image NOTIFICATION_GRAY_ICON = loadImage("/project/libraryclient/Images/Icons/notification-bell-gray.png");
    public static final Image MYBOOK_BLACK_ICON = loadImage("/project/libraryclient/Images/Icons/libraryblack.png");
    public static final Image MYBOOK_GRAY_ICON = loadImage("/project/libraryclient/Images/Icons/librarygray.png");
    public static final Image SETTING_BLACK_ICON = loadImage("/project/libraryclient/Images/Icons/settingblack.png");
    public static final Image SETTING_GRAY_ICON = loadImage("/project/libraryclient/Images/Icons/settinggray.png");
    public static final Image EXIT_BLACK_ICON = loadImage("/project/libraryclient/Images/Icons/xblack.png");
    public static final Image EXIT_GRAY_ICON = loadImage("/project/libraryclient/Images/Icons/x-button.png");
    public static final Image STAR_BLANK_ICON = loadImage("/project/libraryclient/Images/Icons/star.png");
    public static final Image STAR_YELLOW_ICON = loadImage("/project/libraryclient/Images/Icons/staryellow.png");


    // Page link
    public static final String HOMEPAGE_LINK = "/project/libraryclient/FXML/ContentPage/Home.fxml";
    public static final String SETTING_PAGE_LINK = "/project/libraryclient/FXML/ContentPage/Setting.fxml";
    public static final String NOTIFICATION_PAGE_LINK = "/project/libraryclient/FXML/ContentPage/Notification.fxml";
    public static final String MYBOOK_PAGE_LINK = "/project/libraryclient/FXML/ContentPage/MyBook.fxml";
    public static final String SEARCHPAGE_LINK = "/project/libraryclient/FXML/ContentPage/Search.fxml";
    public static final String BOOK_READ_LINK = "/project/libraryclient/FXML/ContentPage/BookReading.fxml";
    public static final String PREVIEW_BOOK_LINK = "/project/libraryclient/FXML/ContentPage/BookPreview.fxml";
    public static final String CLIENT_BORROW_JSON_FILE = "src/main/resources/project/libraryclient/Log/borrow.json";
    public static final String NOTIFICATION_FILE = "src/main/resources/project/libraryclient/Log/notification.txt";


    // ---------------------------------------------------------------- //
// ------------------------- Card ui link ------------------------- //
// ---------------------------------------------------------------- //
    // Book card size 235 x 450
    public static final String CARD_180_320 = "/project/libraryclient/FXML/Card/BookCard_180_320.fxml";

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
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/library_database";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "amogus69420";

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


    public static String noImage = "https://img.freepik.com/premium-vector/default-image-icon-vector-missing-picture-page-website-design-mobile-app-no-photo-available_87543-11093.jpg";

}
