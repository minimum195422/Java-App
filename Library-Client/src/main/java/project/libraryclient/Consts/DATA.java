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


    public static String noImage = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMwAAADACAMAAAB/Pny7AAAAM1BMVEX///+/v7+8vLz8/Pz5+fnt7e3ExMTHx8fPz8/h4eHw8PD29vbY2Njn5+fMzMzU1NS2trYNfAMMAAAEIUlEQVR4nO2c2ZqjIBBGI+573v9pOyZMVHZaC6rn+8/ltAonlFAUmTweAAAAAAAAAFDNPWfqMkZmaEXBF9FVkGEJZLgCGa5AhiuQ4Qpkvjcz4DaZJXeW3PdTc5OMmGJupWE8deiSTNT2gQTISCBDCWQkkKEEMhLIUAIZCWQogYwEMpRARgIZSpLJpFBNIVMOU/F8scxRj48ngcy4yoKWEO1MOkD0MvWyXyWannJwyGWG5lz2pCyvUctU3bkeLUR9T8dNUMv0am1dLL8JtGoIedmoZfSDAjHHtCGZuyHgKmKZUT/0EGtMGx+qrgj5CIhltCh70UZPz+UkxBrQNWKZyXQcFS0zPAvRBsTZX5Apt3ZE778tg0wTK/N+iGhG74XEMrVhAlhi2tieIe/zL1DEMpU+MKKPaeP1CNlKwBRAvc6s+tD4w+XEHqneoaGWGQrFJjY5q9vwO8kTzVnJzZa4gRmX/dbW1ztymW3BO7qEpCUHpuMH4csC6PczZf+NNPGMHJdHfdpANJ6rU2ybx7X5fH9iic0xx/MG4un5KNIUNMa5n6Y5MsLeg3p+4TxTAOtSU61OhZ4pIL9Mbf1m+Lhoi5R7wc0uU3WN5U1Sg2xjcTaSW2ab6yw2daPLNM4sILfM0BY2G9NX89zb1Mwy5SfzMtmYNqmvLMA1O2eW+W4RNBtTkG24poC8MtX3CerYVEthxrURyCtzCCXFxrjf3ppxTQFZZYbj/adIswWZu52sMqcssmh3m7GzuTjLNDll1K3ON9JMy+V+mX0KyCgzqqH0fR/Oib96lb2TGWUmvZ/t26YyFA4O2KeARDKGv82Gd/w9Ns4gc7aUaD/Tawv3aFxINpu9hGGRsWYBaWT6olP/avv429odZIWjFpBE5rX7Fcr+vb7yPzxs5whJZPr3s4//UtpW+CBstYAkBY13WUIcI22+4mKtBaSQkRnY4TTTsCGOwzw0Kb7U8K9eJDrZB9/k68WSBSSQmfeL1o+NPY0MlTHXAuhljiEl3jbl1SCzHdbQy8ynXmyRdu3tlz3NIlMqC8pSja40MhjTFJD6SOMVad4VPgTjFEAu40m0fo2pq9QyN7wfFgwJGrFMeXUStrPq7RHL0A2MqRZALEM3MEWhJ2i0MrPehdswdJZUxlqWvAW9QE0qczWf9DCpvaWUqeylvDvQpwBKGeKB0U8ECGXUL87eznd/lEDGVBi72UaJMzqZy1vjABnlsIZOZlg7cqZUMo8yAecWc5823wpkJJChBDISyFACGQlkKIGM5P+VYfBjbXN/14+1sQMyXIEMVyDDFchwBTJcgQxXIMMVyHAFMlyBDFcgwxXIcAUyXIEMVyDDFchwBTJcgQxXIMMVyHAFMlyBDFcgw5VYma7hTMgPie9UQ82ZoF/fBwAAAAAAAIC/ww/JmYESURpw2wAAAABJRU5ErkJggg==";


}
