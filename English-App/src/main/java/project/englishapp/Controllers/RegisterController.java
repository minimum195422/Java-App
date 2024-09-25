package project.englishapp.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import project.englishapp.App;
import project.englishapp.Consts.SceneData;
import project.englishapp.Models.SceneHandler;

public class RegisterController {

    @SuppressWarnings("unused")
    @FXML
    private AnchorPane Register_MainPane;

    @FXML
    private Button Register_RegisterButton;

    @FXML
    private Button Register_ExitButton;

    @FXML
    private Button Register_GoogleButton;

    @FXML
    private Button Register_AppleButton;

    @FXML
    private Text Register_LoginLink;

    @FXML
    private TextField Register_GetFirstNameField;

    @FXML
    private TextField Register_GetLastNameField;

    @FXML
    private TextField Register_GetEmailField;

    @FXML
    private TextField Register_GetPasswordField;

    @SuppressWarnings("unused")
    @FXML
    private CheckBox Register_CheckBox;
//  //----// Handling UI //----//  //

    //  Exit button
    public void Register_ExitButton_MouseEntered() {
        Register_ExitButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.4);");
    }

    public void Register_ExitButton_MouseExited() {
        Register_ExitButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.2);");
    }

    //  Register button
    public void Register_RegisterButton_MouseEntered() {
        Register_RegisterButton.setStyle("-fx-background-color: #6643c7;");
    }

    public void Register_RegisterButton_MouseExited() {
        Register_RegisterButton.setStyle("-fx-background-color: #6f55b5;");
    }

    // Return to login page link
    public void Register_LoginLink_MouseEntered() {
        Register_LoginLink.setStyle("-fx-fill: #6643c7;");
    }

    public void Register_LoginLink_MouseExited() {
        Register_LoginLink.setStyle("-fx-fill: #6f55b5;");
    }

    //  Textfield
    public void Register_GetFirstNameField_MouseEntered() {
        Register_GetFirstNameField.setStyle("-fx-border-color: #6f55b5; -fx-border-width: 2; -fx-border-radius: 5;");
    }

    public void Register_GetFirstNameField_MouseExited() {
        Register_GetFirstNameField.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
    }

    public void Register_GetLastNameField_MouseEntered() {
        Register_GetLastNameField.setStyle("-fx-border-color: #6f55b5; -fx-border-width: 2; -fx-border-radius: 5;");
    }

    public void Register_GetLastNameField_MouseExited() {
        Register_GetLastNameField.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
    }

    public void Register_GetEmailField_MouseEntered() {
        Register_GetEmailField.setStyle("-fx-border-color: #6f55b5; -fx-border-width: 2; -fx-border-radius: 5;");
    }

    public void Register_GetEmailField_MouseExited() {
        Register_GetEmailField.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
    }

    public void Register_GetPasswordField_MouseEntered() {
        Register_GetPasswordField.setStyle("-fx-border-color: #6f55b5; -fx-border-width: 2; -fx-border-radius: 5;");
    }

    public void Register_GetPasswordField_MouseExited() {
        Register_GetPasswordField.setStyle("-fx-border-color: transparent; -fx-border-width: 0;");
    }

    //  Google button
    public void Register_GoogleButton_MouseEntered() {
        Register_GoogleButton.setStyle("-fx-border-color: #6f55b5;");
    }

    public void Register_GoogleButton_MouseExited() {
        Register_GoogleButton.setStyle("-fx-border-color: #635f6e;");
    }

    //  Apple button
    public void Register_AppleButton_MouseEntered() {
        Register_AppleButton.setStyle("-fx-border-color: #6f55b5;");
    }

    public void Register_AppleButton_MouseExited() {
        Register_AppleButton.setStyle("-fx-border-color: #635f6e;");
    }



//  //----// Handling Function //----//  //

    //  Exit button
    public void Register_ExitButton_Action() {
        Platform.exit();
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
