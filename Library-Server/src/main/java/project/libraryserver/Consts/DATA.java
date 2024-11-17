package project.libraryserver.Consts;

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

    // Home page link
    public static final String HOMEPAGE_LINK = "/project/libraryclient/FXML/ContentPage/Home.fxml";

    // Book card size 235 x 450
    public static final String CARD_235_450 = "/project/libraryclient/FXML/Card/BookCard_180_450.fxml";

// ---------------------------------------------------------------- //
// -------------------------- Scene name -------------------------- //
// ---------------------------------------------------------------- //
    public final static String SCENE_LOGIN_PAGE = "Login Controller";
    public final static String SCENE_REGISTER_PAGE = "Register Controller";
    public final static String SCENE_BEING_DEVELOPMENT = "Being Development";
    public final static String SCENE_DASHBOARD_PAGE = "DashBoard Controller";
    public final static String SCENE_VERIFY_PAGE = "Verify Controller";


// ---------------------------------------------------------------- //
// --------------------- Load image from link --------------------- //
// ---------------------------------------------------------------- //
    private static Image loadImage(String path) {
        return new Image(Objects.requireNonNull(DATA.class.getResourceAsStream(path)));
    }

// ---------------------------------------------------------------- //
// -------------------------- JDBC data --------------------------- //
// ---------------------------------------------------------------- //
    private final static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/englishappdatabase";
    private final static String JDBC_USER = "minimum195422";
    private final static String JDBC_PASSWORD = "Humhayha12#";

    public static String getJdbcUrl() { return JDBC_URL; }

    public static String getJdbcUser() { return JDBC_USER; }

    public static String getJdbcPassword() { return JDBC_PASSWORD; }
}
