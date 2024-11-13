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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;


public class LoginController {
    @FXML
    private Text errorText;
    @FXML
    private Hyperlink SignupLink;
    @FXML
    private TextField username, password;
    @FXML
    private Button Continue;

    @FXML
    private void resetPassword() {
        password.clear();
    };

    @FXML
    private void resetAll() {
        errorText.setVisible(false);
        username.clear();
        password.clear();
    };

    @FXML
    private void handleContinue() throws SQLException {
        String usernameText = username.getText();
        String passwordText = password.getText();
        if (usernameText.isEmpty()) {
            errorText.setVisible(true);
            errorText.setText("Username is empty");
            return;
        }
        if (passwordText.isEmpty()) {
            errorText.setVisible(true);
            errorText.setText("Password is empty");
            return;
        }
        String password = QueryHandler.getPasswordByUsername(usernameText);
        if (!password.equals(passwordText)) {
            errorText.setVisible(true);
            errorText.setText("Username or password is incorrect");
            resetPassword();
            return;
        }
        SceneHandler.getInstance(Main.class, null).setScene("DashboardPage");
    }

    @FXML
    private void handleSignupLink(ActionEvent event) throws IOException {
        SceneHandler.getInstance(Main.class, null).setScene("RegisterPage");
        resetAll();
        // If you want to change the URL name (RegisterPage) then change in Main.java
    }

}