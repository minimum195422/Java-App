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
import project.libraryclient.Database.QueryHandler;
import project.libraryclient.Models.SceneHandler;

import java.net.URL;
import java.sql.SQLException;
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
    };

    private void resetPassword() {
        password.clear();
    };

//  //----// Handling Function //----//  //

    //  Exit button
    public void Login_ExitButton_Action() {
        Platform.exit(); // close program
    }

    //  Login button
    public void LoginButton_MouseClicked() {
        String emailText = email.getText();
        String passwordText = password.getText();
        if (emailText.isEmpty()) {
            errorText.setVisible(true);
            errorText.setText("Email is empty");
            return;
        }
        if (passwordText.isEmpty()) {
            errorText.setVisible(true);
            errorText.setText("Password is empty");
            return;
        }
        try {
            String password = QueryHandler.getPasswordByEmail(emailText);
            if (!password.equals(passwordText)) {
                errorText.setVisible(true);
                errorText.setText("Username or password is incorrect");
                resetPassword();
                return;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        SceneHandler.getInstance(App.class, null).SetScene(DATA.SCENE_DASHBOARD_PAGE);
        resetAll();
    }

    //  Create New Account Link
    public void Login_CreateLink_MouseClicked() {
        SceneHandler.getInstance(App.class, null).SetScene(DATA.SCENE_REGISTER_PAGE); // change to register page
        resetAll();
    }

    //  Apple button
    public void Login_AppleButton_MouseClicked() {
        SceneHandler.getInstance(App.class, null).SetScene(DATA.SCENE_BEING_DEVELOPMENT); // not development yet
        resetAll();
    }

    //  Google button
    public void Login_GoogleButton_MouseClicked() {
        GoogleAuthenticator authenticator = new GoogleAuthenticator();
        authenticator.start();
        resetAll();
    }
}
