package project.libraryserver.Consts;

import javafx.scene.image.Image;

import java.util.Objects;

public class DATA {

// ---------------------------------------------------------------- //
// -------------------- Dashboard content link -------------------- //
// ---------------------------------------------------------------- //




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
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/library";
    private static final String JDBC_USER = "minimum195422";
    private static final String JDBC_PASSWORD = "Humhayha12#";

    public static String getJdbcUrl() { return JDBC_URL; }

    public static String getJdbcUser() { return JDBC_USER; }

    public static String getJdbcPassword() { return JDBC_PASSWORD; }
}
