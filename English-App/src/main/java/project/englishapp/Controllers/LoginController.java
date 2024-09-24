package project.englishapp.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public Button login_button;

    @FXML
    public Text create_account_text;

    @FXML
    private Button exit_button;

    @FXML
    private Button google_button;

    @FXML
    private Button apple_button;

    @FXML
    private TextField textfield1;

    @FXML
    private TextField textfield2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
//  Setup for exit_button
    // close program when click on exit_button
    @SuppressWarnings("unused")
    public void ExitButtonAction(ActionEvent event) {
        Platform.exit();
    }

    @SuppressWarnings("unused")
    public void ExitButtonMouseEntered(MouseEvent event) {
        exit_button.setStyle("-fx-background-color: rgba(255, 255, 255, 0.4);");
    }

    @SuppressWarnings("unused")
    public void ExitButtonMouseExited(MouseEvent event) {
        exit_button.setStyle("-fx-background-color: rgba(255, 255, 255, 0.2);");
    }

//  Setup for textfield
    @SuppressWarnings("unused")
    public void Textfield1MouseEntered(MouseEvent event) {
        textfield1.setStyle("-fx-border-color: #6f55b5; -fx-border-width: 2;");
    }

    @SuppressWarnings("unused")
    public void Textfield1MouseExited(MouseEvent event) {
        textfield1.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
    }

    @SuppressWarnings("unused")
    public void Textfield2MouseEntered(MouseEvent event) {
        textfield2.setStyle("-fx-border-color: #6f55b5; -fx-border-width: 2;");
    }

    @SuppressWarnings("unused")
    public void Textfield2MouseExited(MouseEvent event) {
        textfield2.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
    }

//   setup for another type login button
    // apple
    @SuppressWarnings("unused")
    public void AppleButtonMouseEntered(MouseEvent event) {
        apple_button.setStyle("-fx-border-color: #6f55b5;");
    }

    @SuppressWarnings("unused")
    public void AppleButtonMouseExited(MouseEvent event) {
        apple_button.setStyle("-fx-border-color: #635f6e;");
    }

    // google
    @SuppressWarnings("unused")
    public void GoogleButtonMouseEntered(MouseEvent event) {
        google_button.setStyle("-fx-border-color: #6f55b5;");
    }

    @SuppressWarnings("unused")
    public void GoogleButtonMouseExited(MouseEvent event) {
        google_button.setStyle("-fx-border-color: #635f6e;");
    }

    @SuppressWarnings("unused")
    public void LoginButtonMouseEntered(MouseEvent event) {
        login_button.setStyle("-fx-background-color: #6643c7;");
    }

    @SuppressWarnings("unused")
    public void LoginButtonMouseExited(MouseEvent event) {
        login_button.setStyle("-fx-background-color: #6f55b5;");
    }


//  Setup for create account text
    public void CreateAccountTextMouseEntered(MouseEvent event) {
        create_account_text.setStyle("-fx-fill: #6643c7;");
    }

    public void CreateAccountTextMouseExited(MouseEvent event) {
        create_account_text.setStyle("-fx-fill: #6f55b5;");
    }
}
