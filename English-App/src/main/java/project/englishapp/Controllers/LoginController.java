package project.englishapp.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import project.englishapp.App;
import project.englishapp.Consts.SceneData;
import project.englishapp.JDBC.AppJDBC;
import project.englishapp.Models.SceneHandler;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @SuppressWarnings("unused")
    @FXML
    public AnchorPane Login_MainPane;

    @SuppressWarnings("unused")
    @FXML
    private Button Login_LoginButton;

    @SuppressWarnings("unused")
    @FXML
    private Button Login_ExitButton;

    @SuppressWarnings("unused")
    @FXML
    private Button Login_GoogleButton;

    @SuppressWarnings("unused")
    @FXML
    private Button Login_AppleButton;

    @FXML
    private TextField Login_GetEmailField;

    @FXML
    private TextField Login_GetPasswordField;

    @FXML
    public Text Login_WarningText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

//  //----// Handling Function //----//  //

    //  Exit button
    public void Login_ExitButton_Action() {
        Platform.exit(); // close program
    }

    //  Login button
    public void Login_LoginButton_MouseClicked() throws SQLException {

        if (Login_GetEmailField.getText().isBlank()) {
            Login_WarningText.setText("Enter your email");
            return;
        }
        if (Login_GetPasswordField.getText().isBlank()) {
            Login_WarningText.setText("Enter your password");
            return;
        }
        ValidateLogin();
    }

    public void ValidateLogin() throws SQLException {
        ResultSet resultSet = AppJDBC.getInstance().QueryLoginUserMail(Login_GetEmailField.getText(), Login_GetPasswordField.getText());
        while (resultSet.next()) {
            if (resultSet.getInt(1) == 0) {
                Login_WarningText.setText("Invalid mail or password");
            }
            else {
                SceneHandler.getInstance(App.class, null).SetScene(SceneData.SCENE_DASHBOARD_PAGE);
            }
        }
    }

    //  Create New Account Link
    public void Login_CreateLink_MouseClicked() {
        SceneHandler.getInstance(App.class, null).SetScene(SceneData.SCENE_REGISTER_PAGE); // change to register page
    }

    //  Apple button
    @SuppressWarnings("unused")
    public void Login_AppleButton_MouseClicked() {
        SceneHandler.getInstance(App.class, null).SetScene(SceneData.SCENE_BEING_DEVELOPMENT); // not development yet
    }

    //  Google button
    public void Login_GoogleButton_MouseClicked() {
        SceneHandler.getInstance(App.class, null).SetScene(SceneData.SCENE_BEING_DEVELOPMENT); // not development yet
    }
}