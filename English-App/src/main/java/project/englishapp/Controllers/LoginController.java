package project.englishapp.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.englishapp.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private Stage stage;

    @FXML
    private Button login_button;

    @FXML
    private Text create_account_text;

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
    public void initialize(URL location, ResourceBundle resources) {}

//  Stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }

//  Login button
    // Important : changing scene
    // Using for all scene remaining
    public void LoginButtonMouseClicked(MouseEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXML/Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            if (stage != null) {
                stage.setScene(scene);
            }
        }
        catch (IOException e) {
            System.out.println("Fail to load FXML file");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public void LoginButtonMouseEntered(MouseEvent event) {
        login_button.setStyle("-fx-background-color: #6643c7;");
    }

    @SuppressWarnings("unused")
    public void LoginButtonMouseExited(MouseEvent event) {
        login_button.setStyle("-fx-background-color: #6f55b5;");
    }

//  Exit button
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

//  Textfield
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

//  Setup for create account text
    @SuppressWarnings("unused")
    public void CreateAccountTextMouseEntered(MouseEvent event) {
        create_account_text.setStyle("-fx-fill: #6643c7;");
    }

    @SuppressWarnings("unused")
    public void CreateAccountTextMouseExited(MouseEvent event) {
        create_account_text.setStyle("-fx-fill: #6f55b5;");
    }

}