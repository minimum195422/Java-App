package project.libraryserver.Controllers.DashBoard.Content;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import project.libraryserver.Book.Book;
import project.libraryserver.Database.MySql;
import project.libraryserver.User.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
                System.out.println("Fail to load user");
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
                            ActiveButton.setOnMouseClicked(_ -> {
                                if (!ActiveButton.isSelected() && !InactiveButton.isSelected()) {
                                    ActiveButton.setSelected(true);
                                }
                            });
                            InactiveButton.setOnMouseClicked(_ -> {
                                if (!ActiveButton.isSelected() && !InactiveButton.isSelected()) {
                                    InactiveButton.setSelected(true);
                                }
                            });
                        }
                );
            }
        } catch (SQLException e) {
            System.out.println("Fail at sql");
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
            String status = "";
            if (ActiveButton.isSelected()) {
                status = "active";
            } else {
                status = "inactive";
            }
            MySql.getInstance().UpdateUser(
                Integer.parseInt(DisplayUserId.getText()), DisplayFirstName.getText(), DisplayLastName.getText(),
                    DisplayEmail.getText(), DisplayPassword.getText(), status
            );
            LoadUserList();
        } catch (SQLException e) {
            System.out.println("Thông tin không hợp lệ");
        }
    }

}
