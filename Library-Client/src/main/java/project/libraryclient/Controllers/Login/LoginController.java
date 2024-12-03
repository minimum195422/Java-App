package project.libraryclient.Controllers.Login;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.json.JSONObject;
import project.libraryclient.API.GoogleOauthAPI.GoogleAuthenticator;
import project.libraryclient.App;
import project.libraryclient.Client.Client;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Consts.UserStatus;
import project.libraryclient.Database.MySql;
import project.libraryclient.Models.GenerateJson;
import project.libraryclient.Models.SceneHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @SuppressWarnings("unused")
    @FXML
    public AnchorPane Login_MainPane;
    public Text Login_CreateLink;
    public TextField email;
    public PasswordField password;
    public Text errorText;

    @SuppressWarnings("unused")
    @FXML
    private Button LoginButton;

    @SuppressWarnings("unused")
    @FXML
    private Button ExitButton;

    @SuppressWarnings("unused")
    @FXML
    private Button GoogleButton;

    @SuppressWarnings("unused")
    @FXML
    private Button AppleButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    private void resetAll() {
        errorText.setVisible(false);
        email.clear();
        password.clear();
    }

//  //----// Handling Function //----//  //

    //  Exit button
    public void Exit_Button_Clicked() {
        System.exit(0);
    }

    //  Login button
    public void Login_Button_Clicked() throws IOException, InterruptedException {
        if (email.getText().isEmpty()) {
            SetErrorMessage("Email is empty");
            return;
        }

        if (password.getText().isEmpty()) {
            SetErrorMessage("Password is empty");
            return;
        }

        // Reset trạng thái của client
        Client.getInstance().ResetStatus();

        // Gửi json yêu cầu đăng nhập
        Client.getInstance().SendMessage(
                GenerateJson.CreateNormalLoginRequest(
                        email.getText(), password.getText()
                )
        );

        // Chờ cập nhật trạng thái
        UserStatus status = Client.getInstance().WaitForStatusUpdate();

        // Kiểm tra trạng thái và thực hiện chức năng
        if (status == UserStatus.LOGGED_IN) {
            SceneHandler.getInstance(App.class, null).SetScene(DATA.SCENE_DASHBOARD);
        } else if (status == UserStatus.LOGIN_FAILED) {
            SetErrorMessage("Incorrect login information");
        }
    }

    //  Create New Account Link
    public void Link_To_Create_Account_Clicked() {
        SceneHandler.getInstance(App.class, null).SetScene(DATA.SCENE_REGISTER_PAGE); // change to register page
        resetAll();
    }

    //  Apple button
    public void Apple_Login_Button_Clicked() {
        SetErrorMessage("Please try another method");
    }

    //  Google button
    public void Google_Login_Button_Clicked() throws IOException, InterruptedException {
        // Bắt đầu đăng nhập tài khoản google
        GoogleAuthenticator authenticator = new GoogleAuthenticator();
        authenticator.start();

        // chờ cho tới khi đăng nhập hoàn tất
        authenticator.waitForCompletion();

        // Lấy json trả về từ google
        JSONObject json = authenticator.GetUserInformation();
        if (json != null) {
            // Reset trạng thái của client
            Client.getInstance().ResetStatus();

            // Gửi json yêu cầu đăng nhập
            Client.getInstance().SendMessage(
                    GenerateJson.CreateGoogleLoginRequest(
                            json.getString("id"), json.getString("email")
                    )
            );

            // Chờ cập nhật trạng thái
            UserStatus status = Client.getInstance().WaitForStatusUpdate();

            // Kiểm tra trạng thái và thực hiện chức năng

            if (status == UserStatus.LOGGED_IN) {
                SceneHandler.getInstance(App.class, null).SetScene(DATA.SCENE_DASHBOARD);
            } else if (status == UserStatus.LOGIN_FAILED) {
                SetErrorMessage("Incorrect login information");
            }
        } else {
            SetErrorMessage("Failed to retrieve user information. Please try again.");
        }
    }

    private void SetErrorMessage(String message) {
        Platform.runLater(() -> {
            errorText.setManaged(true);
            errorText.setVisible(true);
            errorText.setText(message);
            errorText.setStyle("");
            errorText.setStyle("-fx-fill: red; -fx-font-size: 12px;");
        });
    }
}
