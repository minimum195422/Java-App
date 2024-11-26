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
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/library_database";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "amogus69420";

    public static String getJdbcUrl() { return JDBC_URL; }

    public static String getJdbcUser() { return JDBC_USER; }

    public static String getJdbcPassword() { return JDBC_PASSWORD; }
}
