package project.libraryclient.Controllers.Register;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.json.JSONObject;
import project.libraryclient.API.GoogleAPI.GoogleAuthenticator;
import project.libraryclient.App;
import project.libraryclient.Client.Client;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Consts.UserStatus;
import project.libraryclient.Models.GenerateJson;
import project.libraryclient.Models.SceneHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

public class RegisterController {
    @SuppressWarnings("unused")
    @FXML
    private AnchorPane MainAnchorPane;

    @SuppressWarnings("unused")
    @FXML
    private Button ExitButton;

    @SuppressWarnings("unused")
    @FXML
    private Button LoginByGoogleButton;

    @SuppressWarnings("unused")
    @FXML
    private Button LoginByAppleButton;

    @SuppressWarnings("unused")
    @FXML
    private Text ReturnToLogin;

    @SuppressWarnings("unused")
    @FXML
    private CheckBox Register_CheckBox;

    @FXML
    public TextField firstName;

    @FXML
    public TextField lastName;

    @FXML
    public Button RegisterButton;

    @FXML
    private Text errorText;

    @FXML
    private TextField email, password, confirmPassword;

    @FXML
    private void resetPassword() {
        password.clear();
        confirmPassword.clear();
    }

    @FXML
    private void resetAll() {
        errorText.setVisible(false);
        errorText.setStyle("");
        errorText.setStyle("-fx-text-fill: red;");
        email.clear();
        password.clear();
        confirmPassword.clear();
        firstName.clear();
        lastName.clear();
    }


//  //----// Handling Function //----//  //

    //  Exit button
    public void Register_ExitButton_Action() {
        System.exit(0);
    }

    //  Register button
    public void Register_RegisterButton_MouseClicked() {

    }

    //  Return to login page link
    public void LoginLink_MouseClicked() {
        SceneHandler.getInstance(App.class, null).SetScene(DATA.SCENE_LOGIN_PAGE);
        resetAll();
    }

    // Google button
    public void Google_Register_Button_Clicked() throws InterruptedException, IOException {
        // Bắt đầu đăng nhập tài khoản google
        GoogleAuthenticator authenticator = new GoogleAuthenticator();
        authenticator.start();

        // chờ cho tới khi đăng nhập hoàn tất
        authenticator.waitForCompletion();

        // Lấy json trả về từ googleS
        JSONObject json = authenticator.GetUserInformation();
        System.out.println("from login" + json);
        if (json != null) {
            // Reset trạng thái của client
            Client.getInstance().ResetStatus();

            // Gửi json yêu cầu đăng kí tài khoản mới
            Client.getInstance().SendMessage(
                    GenerateJson.CreateGoogleRegisterRequest(
                            json.getString("id"),
                            json.getString("given_name"),
                            json.getString("family_name"),
                            json.getString("email"),
                            json.getString("picture")
                    )
            );

            // Chờ cập nhật trạng thái
            UserStatus status = Client.getInstance().WaitForStatusUpdate();

            // Kiểm tra trạng thái và thực hiện chức năng
            if (status == UserStatus.REGISTER_SUCCESS) {
                SceneHandler.getInstance(App.class, null).SetScene(DATA.SCENE_DASHBOARD);
                Client.getInstance().SetStatus(UserStatus.LOGGED_IN);
                SetErrorMessage("Registered successfully! Return to login page");
            } else if (status == UserStatus.REGISTER_FAILED) {
                SetErrorMessage("Email already exists");
            }
        } else {
            SetErrorMessage("Failed to retrieve user information. Please try again.");
        }
    }

    // Apple button
    public void Apple_Login_Button_Clicked() {
//        SceneHandler.getInstance(App.class, null).SetScene(DATA.SCENE_BEING_DEVELOPMENT);
        SetErrorMessage("Please try another method");
    }

    // Handling create new account
    @FXML
    private void RegisterButtonOnclick() throws SQLException, IOException, InterruptedException {
        if (email.getText().isEmpty()) {
//             System.out.println("Email is empty");
            SetErrorMessage("Email is empty");
            resetPassword();
            return;
        }

        if (firstName.getText().isEmpty() || lastName.getText().isEmpty()) {
            // System.out.println("Name can't be empty");
            SetErrorMessage("Name can't be empty");
            resetPassword();
            return;
        }

        if (password.getText().isEmpty()) {
            // System.out.println("Password is empty");
            SetErrorMessage("Password is empty");
            resetPassword();
            return;
        }

        if (!password.getText().equals(confirmPassword.getText())) {
            // System.out.println("Passwords does not match");
            SetErrorMessage("Passwords does not match");
            resetPassword();
            return;
        }

        if (!ValidateEmail(email.getText())) {
            SetErrorMessage("Invalid email");
            resetPassword();
            return;
        }

        // Reset trạng thái của client
        Client.getInstance().ResetStatus();

        // Gửi message từ client tới server
        Client.getInstance().SendMessage(
                GenerateJson.CreateNormalRegisterRequest(
                        firstName.getText(), lastName.getText(), email.getText(), password.getText()
                )
        );

        // chờ tới khi update status mới chuyển tới bước tiếp theo
        UserStatus status = Client.getInstance().WaitForStatusUpdate();

        // kiểm tra trạng thái của status
        if (status == UserStatus.REGISTER_SUCCESS) {
            resetAll();
            SetSuccessMessage();
        } else if (status == UserStatus.REGISTER_FAILED) {
            SetErrorMessage("Email already exists");
            resetPassword();
        }
    }

    private boolean ValidateEmail(String email) throws IOException {
        // url dẫn tới api kiểm tra sự tồn tại của mail
        String url = String.format("https://emailvalidation.abstractapi.com/v1/?api_key=9f45f093bc3a4e5fbacb69d6d7a5bd1a&email=%s", email);
//        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("Response code: " + responseCode);

        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            con.disconnect();

            JSONObject myResponse = new JSONObject(response.toString());
            System.out.println(myResponse);
            // deliverability nghĩa là mail còn hoạt động và có thể sử dụng bình thường
            if (myResponse.getString("deliverability").equals("DELIVERABLE")) {
                return true;
            } else if (myResponse.getString("deliverability").equals("UNDELIVERABLE")) {
                return false;
            }
        }
        con.disconnect();
        return false;
    }

    private void SetErrorMessage(String message) {
        Platform.runLater(() -> {
            errorText.setManaged(true);
            errorText.setVisible(true);
            errorText.setText(message);
            errorText.setStyle("");
            errorText.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
        });
    }

    private void SetSuccessMessage() {
        Platform.runLater(() -> {
            errorText.setManaged(true);
            errorText.setVisible(true);
            errorText.setText("Registered successfully! Return to login page");
            errorText.setStyle("");
            errorText.setStyle("-fx-text-fill: green; -fx-font-size: 12px;");
        });
    }
}
