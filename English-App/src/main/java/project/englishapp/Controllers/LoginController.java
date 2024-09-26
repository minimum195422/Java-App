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

    @SuppressWarnings("unused")
    @FXML
    public AnchorPane Login_MainPane;

    @SuppressWarnings("unused")
    @FXML
    private Button Login_LoginButton;

    @SuppressWarnings("unused")
    @FXML
    private Text Login_CreateLink;

    @SuppressWarnings("unused")
    @FXML
    private Button Login_ExitButton;

    @SuppressWarnings("unused")
    @FXML
    private Button Login_GoogleButton;

    @SuppressWarnings("unused")
    @FXML
    private Button Login_AppleButton;

    @SuppressWarnings("unused")
    @FXML
    private TextField Login_GetEmailField;

    @SuppressWarnings("unused")
    @FXML
    private TextField Login_GetPasswordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

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