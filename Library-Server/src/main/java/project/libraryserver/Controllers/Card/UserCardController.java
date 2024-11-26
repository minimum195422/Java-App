package project.libraryserver.Controllers.Card;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class UserCardController {

    @FXML
    public Label UserId;

    @FXML
    public Label Email;

    @FXML
    public Label Status;

    @FXML
    public Label Name;

    @FXML
    public Circle status_dot;

    public void setInfo(
            String user_id,
            String name,
            String email,
            String status
    ) {
        UserId.setText(user_id);
        Name.setText(name);
        Email.setText(email);
        Status.setText(status);
        if (status.equals("active")) {
            status_dot.getStyleClass().add("circle-glow-green");
        } else {
            status_dot.getStyleClass().add("circle-glow-red");
        }
    }
}
