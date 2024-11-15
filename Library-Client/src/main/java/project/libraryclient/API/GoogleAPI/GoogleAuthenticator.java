package project.libraryclient.API.GoogleAPI;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import project.libraryclient.App;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Models.SceneHandler;

import java.net.URL;
import java.util.ResourceBundle;

// cái này có thể làm demo open web view không được xoá
public class GoogleAuthenticator extends OAuthAuthenticator implements Initializable {

    // Google API Scope
    private String API_SCOPE;


    // Private constructor
    public GoogleAuthenticator() {
        super(DATA.getClientId(), DATA.getClientSecret(), DATA.getRedirectLink());
        this.API_SCOPE = DATA.getGoogleAPIScope();
    }

    @Override
    public void start() {
        Stage stage = new Stage();
        BorderPane root = new BorderPane();

        // Đặt vị trí vào góc màn hình
        root.setLayoutX(Screen.getPrimary().getVisualBounds().getMinX());
        root.setLayoutY(Screen.getPrimary().getVisualBounds().getMinY());

        // Cài kích thước hiển thị bằng với màn hình
        root.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth());
        root.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight());

        // tạo webview
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // URL đăng nhập OAuth2 của Google
        String webURL = "https://accounts.google.com/o/oauth2/v2/auth?scope=" + API_SCOPE +
                "&access_type=offline&redirect_uri=" + getREDIRECT_URI() +
                "&response_type=code&client_id=" + getCLIENT_ID();

        webEngine.load(webURL);

        webEngine.locationProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.startsWith(getREDIRECT_URI())) {
                // Trích xuất mã ủy quyền từ URL
                String authorizationCode = extractAuthorizationCode(newValue);
                if (authorizationCode != null) {
                    // System.out.println("Authorization Code: " + authorizationCode);
                    // Lưu authorization code
                    setAUTHORIZATION_CODE(authorizationCode);

                    // Thực hiện các thao tác tiếp theo, chẳng hạn như lấy token từ mã ủy quyền
                    exchangeCodeForToken();

                    fetchUserInfo();
                    // Điều hướng đến màn hình Dashboard
                    SceneHandler.getInstance(App.class, null).SetScene(DATA.SCENE_DASHBOARD_PAGE);
                }
            }
        });

        root.setCenter(webView);
        stage.setScene(new Scene(root));
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
