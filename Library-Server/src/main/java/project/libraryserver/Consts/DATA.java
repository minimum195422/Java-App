package project.libraryserver.Consts;

import javafx.scene.image.Image;

import java.net.URI;
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
    public static final String BOOK_DISPLAY_CARD_600_200_LINK = "/project/libraryserver/FXML/Cards/BookCard_600_200.fxml";
    public static final String BOOK_DISPLAY_CARD_1020_50_LINK = "/project/libraryserver/FXML/Cards/BookCard_1020_50.fxml";
    public static final String VIEW_DOCUMENT_LINK = "/project/libraryserver/FXML/DashBoard/ViewDocument.fxml";
    public static final String BOOK_BORROW_MANAGE_LINK = "/project/libraryserver/FXML/DashBoard/BorrowManage.fxml";
    public static final String BORROW_BOOK_CARD = "/project/libraryserver/FXML/Cards/BorrowBookCard.fxml";


// ---------------------------------------------------------------- //
// ----------------------- Scene, file name ----------------------- //
// ---------------------------------------------------------------- //
    public final static String SCENE_DASHBOARD_PAGE = "DashBoard Controller";
    public static final String SERVER_LOG_FILE = "src/main/resources/project/libraryserver/Log/log.txt";
    public static final String SERVER_BORROW_JSON_FILE = "src/main/resources/project/libraryserver/Log/borrow.json";

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

// -------------------------------------------------------------------- //
// -------------------------- No image link --------------------------- //
// -------------------------------------------------------------------- //

    public static String noImage = "https://img.freepik.com/premium-vector/default-image-icon-vector-missing-picture-page-website-design-mobile-app-no-photo-available_87543-11093.jpg";
}
