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
    public static final Image NOTIFICATION_BLUE_ICON = loadImage("/project/libraryclient/Images/Icons/notification-bell-blue.png");

    // Discover button items
    public static final Image DISCOVER_WHITE_ICON = loadImage("/project/libraryclient/Images/Icons/compasswhite.png");
    public static final Image DISCOVER_GRAY_ICON = loadImage("/project/libraryclient/Images/Icons/compassgray.png");

    // Mybook button items
    public static final Image MYBOOK_WHITE_ICON = loadImage("/project/libraryclient/Images/Icons/librarywhite.png");
    public static final Image MYBOOK_GRAY_ICON = loadImage("/project/libraryclient/Images/Icons/librarygray.png");

    // Setting button items
    public static final Image SETTING_WHITE_ICON = loadImage("/project/libraryclient/Images/Icons/settingwhite.png");;
    public static final Image SETTING_GRAY_ICON = loadImage("/project/libraryclient/Images/Icons/settinggray.png");;


    public final static String DASHBOARD_HOMEPAGE_LINK = "/project/libraryclient/FXML/ContentPage/Home.fxml";


    private static Image loadImage(String path) {
        return new Image(Objects.requireNonNull(DATA.class.getResourceAsStream(path)));
    }
}
