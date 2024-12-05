package project.libraryclient.Controllers.DashBoard;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import project.libraryclient.Client.Client;
import project.libraryclient.ConfirmDialog.ConfirmDialog;
import project.libraryclient.Models.GenerateJson;

import java.io.IOException;
public class SettingController {
    public TextField NewPassword;
    public Button SaveButton;


    public void SaveButtonClicked() throws IOException {
        boolean confirm = ConfirmDialog.show("Confirm action!","Change your password");
        if (!confirm) return;

        if (NewPassword.getText().isEmpty()) {
            ConfirmDialog.show("New password is blank","Please enter new password");
            return;
        }

        Client.getInstance().SendMessage(
                GenerateJson.CreateChangePasswordRequest(
                        Client.getInstance().getUserId(),
                        NewPassword.getText())
        );

        ConfirmDialog.show("Confirm action!", "Confirmation of successful password change will be displayed in the notification.");
    }
}
