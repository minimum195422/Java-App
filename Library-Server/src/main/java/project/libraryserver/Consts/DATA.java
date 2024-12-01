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
    public static final String BOOK_DISPLAY_CARD_600_200_LINK = "/project/libraryserver/FXML/Cards/BookCard_600_200.fxml";
    public static final String BOOK_DISPLAY_CARD_1020_50_LINK = "/project/libraryserver/FXML/Cards/BookCard_1020_50.fxml";
    public static final String VIEW_DOCUMENT_LINK = "/project/libraryserver/FXML/DashBoard/ViewDocument.fxml";
// ---------------------------------------------------------------- //
// -------------------------- Scene name -------------------------- //
// ---------------------------------------------------------------- //
    public final static String SCENE_DASHBOARD_PAGE = "DashBoard Controller";
    public static final String SERVER_LOG_FILE = "src/main/resources/project/libraryserver/Log/log.txt";



    // ---------------------------------------------------------------- //
// --------------------- Load image from link --------------------- //
// ---------------------------------------------------------------- //
    public static String noImage = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMwAAADACAMAAAB/Pny7AAAAM1BMVEX///+/v7+8vLz8/Pz5+fnt7e3ExMTHx8fPz8/h4eHw8PD29vbY2Njn5+fMzMzU1NS2trYNfAMMAAAEIUlEQVR4nO2c2ZqjIBBGI+573v9pOyZMVHZaC6rn+8/ltAonlFAUmTweAAAAAAAAAFDNPWfqMkZmaEXBF9FVkGEJZLgCGa5AhiuQ4Qpkvjcz4DaZJXeW3PdTc5OMmGJupWE8deiSTNT2gQTISCBDCWQkkKEEMhLIUAIZCWQogYwEMpRARgIZSpLJpFBNIVMOU/F8scxRj48ngcy4yoKWEO1MOkD0MvWyXyWannJwyGWG5lz2pCyvUctU3bkeLUR9T8dNUMv0am1dLL8JtGoIedmoZfSDAjHHtCGZuyHgKmKZUT/0EGtMGx+qrgj5CIhltCh70UZPz+UkxBrQNWKZyXQcFS0zPAvRBsTZX5Apt3ZE778tg0wTK/N+iGhG74XEMrVhAlhi2tieIe/zL1DEMpU+MKKPaeP1CNlKwBRAvc6s+tD4w+XEHqneoaGWGQrFJjY5q9vwO8kTzVnJzZa4gRmX/dbW1ztymW3BO7qEpCUHpuMH4csC6PczZf+NNPGMHJdHfdpANJ6rU2ybx7X5fH9iic0xx/MG4un5KNIUNMa5n6Y5MsLeg3p+4TxTAOtSU61OhZ4pIL9Mbf1m+Lhoi5R7wc0uU3WN5U1Sg2xjcTaSW2ab6yw2daPLNM4sILfM0BY2G9NX89zb1Mwy5SfzMtmYNqmvLMA1O2eW+W4RNBtTkG24poC8MtX3CerYVEthxrURyCtzCCXFxrjf3ppxTQFZZYbj/adIswWZu52sMqcssmh3m7GzuTjLNDll1K3ON9JMy+V+mX0KyCgzqqH0fR/Oib96lb2TGWUmvZ/t26YyFA4O2KeARDKGv82Gd/w9Ns4gc7aUaD/Tawv3aFxINpu9hGGRsWYBaWT6olP/avv429odZIWjFpBE5rX7Fcr+vb7yPzxs5whJZPr3s4//UtpW+CBstYAkBY13WUIcI22+4mKtBaSQkRnY4TTTsCGOwzw0Kb7U8K9eJDrZB9/k68WSBSSQmfeL1o+NPY0MlTHXAuhljiEl3jbl1SCzHdbQy8ynXmyRdu3tlz3NIlMqC8pSja40MhjTFJD6SOMVad4VPgTjFEAu40m0fo2pq9QyN7wfFgwJGrFMeXUStrPq7RHL0A2MqRZALEM3MEWhJ2i0MrPehdswdJZUxlqWvAW9QE0qczWf9DCpvaWUqeylvDvQpwBKGeKB0U8ECGXUL87eznd/lEDGVBi72UaJMzqZy1vjABnlsIZOZlg7cqZUMo8yAecWc5823wpkJJChBDISyFACGQlkKIGM5P+VYfBjbXN/14+1sQMyXIEMVyDDFchwBTJcgQxXIMMVyHAFMlyBDFcgwxXIcAUyXIEMVyDDFchwBTJcgQxXIMMVyHAFMlyBDFcgw5VYma7hTMgPie9UQ82ZoF/fBwAAAAAAAIC/ww/JmYESURpw2wAAAABJRU5ErkJggg==";

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
