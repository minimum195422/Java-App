package project.libraryserver.Controllers.DashBoard.Content;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import project.libraryserver.ConfirmDialog.ConfirmDialog;
import project.libraryserver.Database.MySql;
import project.libraryserver.User.User;
import project.libraryserver.User.UserSort;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageUsersController implements Initializable {
    @FXML
    public VBox DisplayUserList;

    @FXML
    public TextField DisplayUserId, DisplayFirstName, DisplayLastName, DisplayEmail,
            DisplayPassword, SearchBox;
    @FXML
    public ToggleButton ActiveButton, InactiveButton, DeleteAccountButton, ChangeButton,
            SortByIdButton, SortByEmailButton, SortByNameButton, SortDirection;

    public ToggleGroup DisplayStatus = new ToggleGroup();
    public ToggleGroup SortUserGroup = new ToggleGroup();

    @FXML
    public Text WarningText;

    @SuppressWarnings("FieldCanBeLocal")
    private ArrayList<User> UserList = new ArrayList<>();

    User SelectedUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ActiveButton.setToggleGroup(DisplayStatus);
        InactiveButton.setToggleGroup(DisplayStatus);

        // Set button group always have one in used
        DisplayStatus.selectedToggleProperty().addListener(
                (_, oldToggle, newToggle) -> {
            if (newToggle == null) {
                DisplayStatus.selectToggle(oldToggle);
            }
        });

        SortByIdButton.setToggleGroup(SortUserGroup);
        SortByEmailButton.setToggleGroup(SortUserGroup);
        SortByNameButton.setToggleGroup(SortUserGroup);

        // Set button group always have one in used
        SortUserGroup.selectedToggleProperty().addListener(
            (_, oldToggle, newToggle) -> {
                if (newToggle == null) {
                    SortUserGroup.selectToggle(oldToggle);
            }
        });


        // Default sort direction
        SortDirection.setSelected(true);
        SortByIdButton.setSelected(true);

        LoadUserList();
    }

    private void LoadUserList() {
        try {
            UserList = MySql.getInstance().GetAllUser();
            if (UserList.isEmpty()) {
                System.out.println("user list is empty");
                return;
            }

            SortUserList();
        } catch (SQLException e) {
            System.out.println("fail at sql");
            e.printStackTrace(System.out);
        }
    }

    public void DeleteButtonClicked() {
        if (SelectedUser == null) {
            WarningText.setText("No data selected");
            return;
        }

        // Popup confirm delete user action
        boolean confirmed = ConfirmDialog.show(
                "Confirm delete",
                "Confirm delete user from list!"
        );
        if (!confirmed) return;

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                try {
                    MySql.getInstance().DeleteUser(
                            Integer.parseInt(DisplayUserId.getText())
                    );

                    // After the update, load the user list
                    Platform.runLater(() -> {
                        LoadUserList();
                        WarningText.setText("Delete user successfully");
                    });
                } catch (NumberFormatException e) {
                    Platform.runLater(() -> WarningText.setText("ID không hợp lệ"));
                    System.err.println("ID không hợp lệ: " + e.getMessage());
                }
                return null;
            }
        };

        new Thread(task).start();

        WarningText.setText("Delete user ...");
    }

    public void ChangeButtonClicked() {
        if (SelectedUser == null) {
            WarningText.setText("No data selected");
            return;
        }

        // Popup confirm change user information action
        boolean confirmed = ConfirmDialog.show(
                "Confirm change",
                "Confirm changing user information!"
        );
        if (!confirmed) return;

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                try {
                    MySql.getInstance().UpdateUser(
                            Integer.parseInt(DisplayUserId.getText()),
                            DisplayFirstName.getText(),
                            DisplayLastName.getText(),
                            DisplayEmail.getText(),
                            DisplayPassword.getText(),
                            (ActiveButton.isSelected() && !InactiveButton.isSelected()) ?
                                    "active" : "inactive"
                    );

                    // After the update, load the user list on the UI thread
                    Platform.runLater(() -> {
                        LoadUserList();
                        WarningText.setText("User information updated successfully");
                    });
                } catch (NumberFormatException e) {
                    Platform.runLater(() -> WarningText.setText("ID không hợp lệ"));
                    System.err.println("ID không hợp lệ: " + e.getMessage());
                } catch (SQLException e) {
                    Platform.runLater(() -> WarningText.setText("Lỗi mysql: " + e.getMessage()));
                    System.out.println("Lỗi mysql: " + e.getMessage());
                }
                return null;
            }
        };

        new Thread(task).start();

        WarningText.setText("Updating user information...");
    }

    public void SearchBoxOnAction() {
        if (!DisplayUserList.getChildren().isEmpty()) DisplayUserList.getChildren().clear();
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws SQLException {
                UserList = MySql.getInstance().GetSearchUserList(SearchBox.getText());

                Platform.runLater(() -> {
                    if (UserList.isEmpty()) return;

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
                                    SelectedUser = u;
                                }
                        );
                    }
                });

                return null;
            }
        };

        new Thread(task).start();
    }

    public void SortUserList() {
        if (SortByIdButton.isSelected()) {
            if (SortDirection.isSelected()) {
                UserSort.SortByIdAsc(UserList);
            } else {
                UserSort.SortByIdDesc(UserList);
            }
        }
        if (SortByNameButton.isSelected()) {
            if (SortDirection.isSelected()) {
                UserSort.SortByNameAsc(UserList);
            } else {
                UserSort.SortByNameDesc(UserList);
            }
        }
        if (SortByEmailButton.isSelected()) {
            if (SortDirection.isSelected()) {
                UserSort.SortByEmailAsc(UserList);
            } else {
                UserSort.SortByEmailDesc(UserList);
            }
        }
        reloadDisplayList();
    }

    private void reloadDisplayList() {
        if (!DisplayUserList.getChildren().isEmpty()) DisplayUserList.getChildren().clear();
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
                        SelectedUser = u;
                    }
            );
        }
    }
}
