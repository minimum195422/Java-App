package com.example.demo;

import com.example.demo.backend.MySQLConnection;
import com.example.demo.library.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.Connection;
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
        Connection connection = MySQLConnection.connectToDB();
        String SQL = "SELECT COUNT(*) FROM accounts WHERE username = ? AND password = ?";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        stmt.setString(1, usernameText);
        stmt.setString(2, passwordText);
        ResultSet rs = stmt.executeQuery();
        boolean existed = false;
        while (rs.next()) {
            existed = rs.getBoolean(1);
        }
        if (!existed) {
            resetPassword();
            Continue.setText("LMAO");
        }
        else {
            Continue.setText("OK");
        }
        connection.close();
    }

    public void handleSignupLink(ActionEvent event) throws IOException {
        SceneHandler.getInstance(Main.class, null).setScene("RegisterPage");
        resetAll();
        // If you want to change the URL name (RegisterPage) then change in Main.java
    }

}