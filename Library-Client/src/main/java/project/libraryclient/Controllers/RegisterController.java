package project.libraryclient.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import project.libraryclient.App;
import project.libraryclient.Consts.SceneData;
import project.libraryclient.Models.SceneHandler;

public class RegisterController {
    @SuppressWarnings("unused")
    @FXML
    private AnchorPane Register_MainPane;

    @SuppressWarnings("unused")
    @FXML
    private Button Register_RegisterButton;

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
    private TextField Register_GetFirstNameField;

    @SuppressWarnings("unused")
    @FXML
    private TextField Register_GetLastNameField;

    @SuppressWarnings("unused")
    @FXML
    private TextField Register_GetEmailField;

    @SuppressWarnings("unused")
    @FXML
    private TextField Register_GetPasswordField;

    @SuppressWarnings("unused")
    @FXML
    private CheckBox Register_CheckBox;

//  //----// Handling Function //----//  //

    //  Exit button
    public void Register_ExitButton_Action() {
        Platform.exit(); // close program
    }

    //  Register button
    public void Register_RegisterButton_MouseClicked() {
        SceneHandler.getInstance(App.class, null).SetScene(SceneData.SCENE_BEING_DEVELOPMENT);
    }

    //  Return to login page link
    public void Register_LoginLink_MouseClicked() {
        SceneHandler.getInstance(App.class, null).SetScene(SceneData.SCENE_LOGIN_PAGE);
    }

    // Google button
    public void Register_GoogleButton_MouseClicked() {
        SceneHandler.getInstance(App.class, null).SetScene(SceneData.SCENE_BEING_DEVELOPMENT);
    }

    // Apple button
    public void Register_AppleButton_MouseClicked() {
        SceneHandler.getInstance(App.class, null).SetScene(SceneData.SCENE_BEING_DEVELOPMENT);
    }
}
