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
import project.englishapp.Models.SceneHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public AnchorPane Login_MainPane;

    @FXML
    private Button Login_LoginButton;

    @FXML
    private Text Login_CreateLink;

    @FXML
    private Button Login_ExitButton;

    @FXML
    private Button Login_GoogleButton;

    @FXML
    private Button Login_AppleButton;

    @FXML
    private TextField Login_GetEmailField;

    @FXML
    private TextField Login_GetPasswordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

//  //----// Handling UI //----//  //
    // Exit button
    public void Login_ExitButton_MouseEntered() {
        Login_ExitButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.4);");
    }

    public void Login_ExitButton_MouseExited() {
        Login_ExitButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.2);");
    }

    //  Login button
    public void Login_LoginButton_MouseEntered() {
        Login_LoginButton.setStyle("-fx-background-color: #6643c7;");
    }

    public void Login_LoginButton_MouseExited() {
        Login_LoginButton.setStyle("-fx-background-color: #6f55b5;");
    }

    // Create New Account Link
    public void Login_CreateLink_MouseEntered() {
        Login_CreateLink.setStyle("-fx-fill: #6643c7;");
    }

    public void Login_CreateLink_MouseExited() {
        Login_CreateLink.setStyle("-fx-fill: #6f55b5;");
    }

    // Textfield
    public void Login_GetEmailField_MouseEntered() {
        Login_GetEmailField.setStyle("-fx-border-color: #6f55b5; -fx-border-width: 2; -fx-border-radius: 5;");
    }

    public void Login_GetEmailField_MouseExited() {
        Login_GetEmailField.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
    }

    public void Login_GetPasswordField_MouseEntered() {
        Login_GetPasswordField.setStyle("-fx-border-color: #6f55b5; -fx-border-width: 2; -fx-border-radius: 5;");
    }

    public void Login_GetPasswordField_MouseExited() {
        Login_GetPasswordField.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
    }

    //  Google button
    public void Login_GoogleButton_MouseEntered() {
        Login_GoogleButton.setStyle("-fx-border-color: #6f55b5;");
    }

    public void Login_GoogleButton_MouseExited() {
        Login_GoogleButton.setStyle("-fx-border-color: #635f6e;");
    }

    //  Apple button
    public void Login_AppleButton_MouseEntered() {
        Login_AppleButton.setStyle("-fx-border-color: #6f55b5;");
    }

    public void Login_AppleButton_MouseExited() {
        Login_AppleButton.setStyle("-fx-border-color: #635f6e;");
    }

//  //----// Handling Function //----//  //
    //  Exit button
    public void Login_ExitButton_Action() {
        Platform.exit(); // close program
    }

    //  Login button
    public void Login_LoginButton_MouseClicked() {
        SceneHandler.getInstance(App.class, null).SetScene(SceneData.SCENE_BEING_DEVELOPMENT); // not development yet
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