package project.libraryserver.User;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import project.libraryserver.Consts.DATA;
import project.libraryserver.Controllers.Card.UserCardController;

import java.io.IOException;

@SuppressWarnings("FieldMayBeFinal")
public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String status;
    private String password;
    AnchorPane DisplayCard;
    UserCardController Controller;

    public User(
            int id,
            String first_name,
            String last_name,
            String email,
            String status,
            String password) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.status = status;
        this.password = password;
        LoadDisplayCard();
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public AnchorPane getDisplayCard() {
        return this.DisplayCard;
    }

    public UserCardController getController() {
        return this.Controller;
    }

    private void LoadDisplayCard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(DATA.USER_DISPLAY_CARD_LINK));
            DisplayCard = loader.load();
            Controller = loader.getController();
            Controller.setInfo(
                    String.valueOf(this.id),
                    this.first_name + " " + this.last_name,
                    this.email,
                    this.status);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
