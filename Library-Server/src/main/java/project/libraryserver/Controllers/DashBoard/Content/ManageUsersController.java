package project.libraryserver.Controllers.DashBoard.Content;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import project.libraryserver.Database.MySql;
import project.libraryserver.User.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManageUsersController implements Initializable {
    @FXML
    public VBox DisplayUserList;

    @FXML
    public TextField DisplayUserId;

    @FXML
    public TextField DisplayFirstName;

    @FXML
    public TextField DisplayLastName;

    @FXML
    public TextField DisplayEmail;

    @FXML
    public ToggleButton ActiveButton;

    @FXML
    public ToggleButton InactiveButton;
    @FXML
    public TextField DisplayPassword;

    public ToggleGroup DisplayStatus = new ToggleGroup();
    public ToggleButton DeleteAccountButton;
    public ToggleButton ChangeButton;


    private List<User> UserList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ActiveButton.setToggleGroup(DisplayStatus);
        InactiveButton.setToggleGroup(DisplayStatus);
        LoadUserList();
    }

    private void LoadUserList() {
        if (!UserList.isEmpty()) UserList.clear();
        if (!DisplayUserList.getChildren().isEmpty()) DisplayUserList.getChildren().clear();

        try {
            UserList = MySql.getInstance().GetUserList();
            if (UserList.isEmpty()) {
                System.out.println("fail to load user");
            }

            for (User u : UserList) {
                DisplayUserList.getChildren().add(
                        u.getDisplayCard()
                );
                u.getDisplayCard().setOnMouseClicked(
                        _ -> {
                            DisplayUserId.setText(String.valueOf(u.getId()));
                            DisplayFirstName.setText(u.getFirst_name());
                            DisplayLastName.setText(u.getLast_name());
                            DisplayEmail.setText(u.getEmail());
                            DisplayPassword.setText(u.getPassword());
                            if (u.getStatus().equals("active")) {
                                ActiveButton.setSelected(true);
                            } else {
                                InactiveButton.setSelected(true);
                            }
                        }
                );
            }
        } catch (SQLException e) {
            System.out.println("fail at sql");
            e.printStackTrace(System.out);
        }

    }

    public void DeleteButtonClicked() throws SQLException {
        try {
            MySql.getInstance().DeleteUser(
                    Integer.parseInt(DisplayUserId.getText())
            );
            LoadUserList();
        } catch (NumberFormatException e) {
            System.err.println("ID không hợp lệ: " + e.getMessage());
        }
    }

    public void ChangeButtonClicked() {
        try {
            MySql.getInstance().UpdateUser(

            );
            LoadUserList();
        } catch (SQLException e) {
            System.out.println("Thông tin không hợp lệ");
        }
    }

}
