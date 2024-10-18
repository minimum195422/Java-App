package com.example.demo;

import com.example.demo.backend.MySQLConnection;
import com.example.demo.library.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
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
            resetPassword();
            return;
        }
        Connection connection = MySQLConnection.connectToDB();
        String SQL = "SELECT COUNT(*) FROM accounts WHERE email = ?";
        PreparedStatement stmt = connection.prepareStatement(SQL);
        stmt.setString(1, emailText);
        ResultSet rs = stmt.executeQuery();
        boolean existed = false;
        while (rs.next()) {
//            System.out.println(rs.getInt(1));
            existed = rs.getBoolean(1);
        }
        if (existed) {
            System.out.println("Email already exists");
            errorText.setText("Email already exists");
            resetEmail();
            return;
        }
        SQL = "SELECT COUNT(*) FROM accounts WHERE username = ?";
        stmt = connection.prepareStatement(SQL);
        stmt.setString(1, usernameText);
        rs = stmt.executeQuery();
        while (rs.next()) {
//            System.out.println(rs.getInt(1));
            existed = rs.getBoolean(1);
        }
        if (existed) {
            System.out.println("Username already exists");
            errorText.setText("Username already exists");
            resetUsername();
            return;
        }
        SQL = "INSERT INTO accounts(email, username, password) VALUES(?, ?, ?)";
        stmt = connection.prepareStatement(SQL);
        stmt.setString(1, emailText);
        stmt.setString(2, usernameText);
        stmt.setString(3, passwordText);
        int status = stmt.executeUpdate();
        errorText.setText("Registered successfully");
        errorText.setStyle("-fx-fill: green;");
        resetAll();
//        System.out.println(status);
        connection.close();
    }

    public void handleSigninLink(ActionEvent event) throws IOException {
        SceneHandler.getInstance(Main.class, null).setScene("LoginPage");
        resetAll();
        // If you want to change the URL name (LoginPage) then change in Main.java
    }
}
