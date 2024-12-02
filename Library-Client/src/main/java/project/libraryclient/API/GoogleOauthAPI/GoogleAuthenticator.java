package project.libraryclient.API.GoogleOauthAPI;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.json.JSONObject;
import project.libraryclient.Consts.DATA;

import java.util.concurrent.CountDownLatch;

public class GoogleAuthenticator extends OAuthAuthenticator {

    private final CountDownLatch latch = new CountDownLatch(1);

    public GoogleAuthenticator() {
        super();
    }

    @SuppressWarnings("unused")
    @Override
    public void start() {
        Stage stage = new Stage();

        // chặn luồng chính cho tới khi luồng này đóng
        stage.initModality(Modality.APPLICATION_MODAL);
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
        String webURL = "https://accounts.google.com/o/oauth2/v2/auth?scope=" + DATA.GetGoogleAPIScope() +
                "&access_type=offline&redirect_uri=" + DATA.GetGoogleRedirectLink() +
                "&response_type=code&client_id=" + DATA.GetGoogleClientId();

        webEngine.load(webURL);

        webEngine.locationProperty().addListener((observable, oldValue, newValue) -> {
            // System.out.println("Navigated to: " + newValue); // use this to check web link
            if (newValue.startsWith(DATA.GetGoogleRedirectLink())) {
                // Trích xuất mã ủy quyền từ URL
                String authorizationCode = extractAuthorizationCode(newValue);
                if (authorizationCode != null) {
                    // System.out.println("Authorization Code: " + authorizationCode);
                    // Lưu authorization code
                    SetAUTHORIZATION_CODE(authorizationCode);

                    // Thực hiện các thao tác tiếp theo, chẳng hạn như lấy token từ mã ủy quyền
                    exchangeCodeForToken();

                    JSONObject tmp = fetchUserInfo();

                    SetUserInformation(tmp);

                    // tín hiệu hoàn thành đăng nhập
                    latch.countDown();
                    stage.close();
                }
            }
        });

        root.setCenter(webView);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void waitForCompletion() throws InterruptedException {
        latch.await(); // Chờ cho đến khi latch được giải phóng
    }
}
