package project.libraryserver.Consts;

import javafx.scene.image.Image;

import java.util.Objects;

public class DATA {

// ---------------------------------------------------------------- //
// -------------------- Dashboard content link -------------------- //
// ---------------------------------------------------------------- //
    public static final String DASHBOAD_LINK = "FXML/DashBoard/DashBoard.fxml";
    public static final String SERVER_LOG_LINK = "/project/libraryserver/FXML/DashBoard/ServerLog.fxml";
    public static final String MANAGE_USERS_LINK = "/project/libraryserver/FXML/DashBoard/ManageUsers.fxml";
    public static final String MANAGE_DOCUMENT_LINK = "/project/libraryserver/FXML/DashBoard/ManageDocument.fxml";
    public static final String ADD_NEW_BOOK_LINK = "/project/libraryserver/FXML/DashBoard/AddNewBook.fxml";
    public static final String USER_DISPLAY_CARD_LINK = "/project/libraryserver/FXML/Cards/UserCard.fxml";
    public static final String BOOK_DISPLAY_CARD_LINK = "/project/libraryserver/FXML/Cards/BookCard_600_200.fxml";

// ---------------------------------------------------------------- //
// -------------------------- Scene name -------------------------- //
// ---------------------------------------------------------------- //
    public final static String SCENE_DASHBOARD_PAGE = "DashBoard Controller";



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

    public static String getJdbcUrl() { return JDBC_URL; }

    public static String getJdbcUser() { return JDBC_USER; }

    public static String getJdbcPassword() { return JDBC_PASSWORD; }
}
