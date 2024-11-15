package Frontend.Controller;

import Backend.QueryHandler;
import Frontend.Main;
import Frontend.Library.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    private Text errorText;
    @FXML
    private TextField email, username, password, confirmPassword;
    @FXML
    private Hyperlink SigninLink;
    @FXML
    private Button Continue;

    @FXML
    private void resetPassword() {
        password.clear();
        confirmPassword.clear();
    }

    @FXML
    private void resetUsername() {
        username.clear();
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
        username.clear();
        password.clear();
        confirmPassword.clear();
    }

    @FXML
    private void handleContinue() throws SQLException {
        errorText.setVisible(true);
        String emailText = email.getText();
        String usernameText = username.getText();
        String passwordText = password.getText();
        String confirmPasswordText = confirmPassword.getText();
        if (emailText.isEmpty()) {
            System.out.println("Email is empty");
            errorText.setText("Email is empty");
            return;
        }
        if (usernameText.isEmpty()) {
            System.out.println("Username is empty");
            errorText.setText("Username is empty");
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
        boolean existed = QueryHandler.checkAccountByEmail(emailText);
        if (existed) {
            System.out.println("Email already exists");
            errorText.setText("Email already exists");
//            resetEmail();
            return;
        }
        existed = QueryHandler.checkAccountByUsername(usernameText);
        if (existed) {
            System.out.println("Username already exists");
            errorText.setText("Username already exists");
//            resetUsername();
            return;
        }
        QueryHandler.addNewAccount(emailText, usernameText, passwordText);
        resetAll();
        errorText.setText("Registered successfully");
        errorText.setVisible(true);
        errorText.setStyle("-fx-fill: green;");
    }

    public void handleSigninLink(ActionEvent event) {
        SceneHandler.getInstance(Main.class, null).setScene("LoginPage");
        resetAll();
        // If you want to change the URL name (LoginPage) then change in Main.java
    }
}
