package project.libraryclient.Controllers.Login;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import project.libraryclient.API.GoogleAPI.GoogleAuthenticator;
import project.libraryclient.App;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Models.SceneHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @SuppressWarnings("unused")
    @FXML
    public AnchorPane Login_MainPane;

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

    @FXML
    private TextField Login_GetEmailField;

    @FXML
    private PasswordField Login_GetPasswordField;

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
    public void LoginButton_MouseClicked() {
        if (Login_GetEmailField.getText().isBlank()) {
            Login_WarningText.setText("Enter your email");
        } else if (Login_GetPasswordField.getText().isBlank()) {
            Login_WarningText.setText("Enter your password");
        }
    }

    //  Create New Account Link
    public void Login_CreateLink_MouseClicked() {
        SceneHandler.getInstance(App.class, null).SetScene(DATA.SCENE_REGISTER_PAGE); // change to register page
    }

    //  Apple button
    public void Login_AppleButton_MouseClicked() {
        SceneHandler.getInstance(App.class, null).SetScene(DATA.SCENE_BEING_DEVELOPMENT); // not development yet
    }

    //  Google button
    public void Login_GoogleButton_MouseClicked() {
        GoogleAuthenticator authenticator = new GoogleAuthenticator();
        authenticator.start();
    }
}
