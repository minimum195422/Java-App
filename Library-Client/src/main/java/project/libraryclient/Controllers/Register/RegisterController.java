package project.libraryclient.Controllers.Register;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import project.libraryclient.App;
import project.libraryclient.Consts.DATA;
import project.libraryclient.Database.QueryHandler;
import project.libraryclient.Models.SceneHandler;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController {
    @SuppressWarnings("unused")
    @FXML
    private AnchorPane Register_MainPane;

    @SuppressWarnings("unused")
    @FXML
    private Button Register_ExitButton;

    @SuppressWarnings("unused")
    @FXML
    private Button Register_GoogleButton;

    @SuppressWarnings("unused")
    @FXML
    private Button Register_AppleButton;

    @SuppressWarnings("unused")
    @FXML
    private Text Register_LoginLink;

    @SuppressWarnings("unused")
    @FXML
    private CheckBox Register_CheckBox;

    @FXML
    public TextField firstName;

    @FXML
    public TextField lastName;

    @FXML
    public Button Register_RegisterButton;

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
    private void resetEmail() {
        email.clear();
    }

    @FXML
    private void resetAll() {
        errorText.setVisible(false);
        errorText.setStyle("-fx-fill: red;");
        email.clear();
        password.clear();
        confirmPassword.clear();
        firstName.clear();
        lastName.clear();
    }


//  //----// Handling Function //----//  //

    //  Exit button
    public void Register_ExitButton_Action() {
        Platform.exit(); // close program
    }

    //  Register button
    public void Register_RegisterButton_MouseClicked() {
        SceneHandler.getInstance(App.class, null).SetScene(DATA.SCENE_REGISTER_PAGE);
        resetAll();
    }

    //  Return to login page link
    public void Register_LoginLink_MouseClicked() {
        SceneHandler.getInstance(App.class, null).SetScene(DATA.SCENE_LOGIN_PAGE);
        resetAll();
    }

    // Google button
    public void Register_GoogleButton_MouseClicked() {
        SceneHandler.getInstance(App.class, null).SetScene(DATA.SCENE_BEING_DEVELOPMENT);
        resetAll();
    }

    // Apple button
    public void Register_AppleButton_MouseClicked() {
        SceneHandler.getInstance(App.class, null).SetScene(DATA.SCENE_BEING_DEVELOPMENT);
        resetAll();
    }

    // Submit account
    @FXML
    private void handleContinue() throws SQLException {
        errorText.setStyle("-fx-fill: red;");
        errorText.setVisible(true);
        String emailText = email.getText();
        String passwordText = password.getText();
        String confirmPasswordText = confirmPassword.getText();
        String username = firstName.getText() + " " + lastName.getText();
        if (emailText.isEmpty()) {
            System.out.println("Email is empty");
            errorText.setText("Email is empty");
            return;
        }
        if (passwordText.isEmpty()) {
            System.out.println("Password is empty");
            errorText.setText("Password is empty");
            return;
        }
        if (!passwordText.equals(confirmPasswordText)) {
            System.out.println("Passwords does not match");
            errorText.setText("Passwords does not match");
//            resetPassword();
            return;
        }
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty()) {
            System.out.println("Name can't be empty");
            errorText.setText("Name can't be empty");
            return;
        }
        boolean existed = QueryHandler.checkAccountByEmail(emailText);
        if (existed) {
            System.out.println("Email already exists");
            errorText.setText("Email already exists");
//            resetEmail();
            return;
        }
        QueryHandler.addNewAccount(emailText, username, passwordText);
        resetAll();
        errorText.setText("Registered successfully! Return to login page");
        errorText.setVisible(true);
        errorText.setStyle("-fx-fill: green;");
    }
}

// Check lai email