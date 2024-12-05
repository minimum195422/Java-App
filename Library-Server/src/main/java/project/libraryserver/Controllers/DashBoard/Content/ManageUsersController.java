package project.libraryserver.Controllers.DashBoard.Content;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import project.libraryserver.ConfirmDialog.*;
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
    public ToggleGroup SortUserGroup = new ToggleGroup();

    @FXML
    public ToggleButton DeleteAccountButton;

    @FXML
    public ToggleButton ChangeButton;

    @FXML
    public ToggleButton SortByIdButton;

    @FXML
    public ToggleButton SortByEmailButton;

    @FXML
    public ToggleButton SortByNameButton;

    @FXML
    public ToggleButton SortDirection;

    @FXML
    public Label WarningText;

    @FXML
    public TextField SearchBox;

    @SuppressWarnings("FieldCanBeLocal")
    private ArrayList<User> UserList = new ArrayList<>();

    User SelectedUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ActiveButton.setToggleGroup(DisplayStatus);
        InactiveButton.setToggleGroup(DisplayStatus);
        WarningText.setStyle("-fx-text-fill: red;");
        // Set button group always have one in used
        DisplayStatus.selectedToggleProperty().addListener((_, oldToggle, newToggle) -> {
            if (newToggle == null) {
                DisplayStatus.selectToggle(oldToggle);
            }
        });

        SortByIdButton.setToggleGroup(SortUserGroup);
        SortByEmailButton.setToggleGroup(SortUserGroup);
        SortByNameButton.setToggleGroup(SortUserGroup);

        // Set button group always have one in used
        SortUserGroup.selectedToggleProperty().addListener((_, oldToggle, newToggle) -> {
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
                System.out.println("Fail to load user");
                return;
            }

            SortUserList();
        } catch (SQLException e) {
            System.out.println("Fail at sql");
            e.printStackTrace(System.out);
        }
    }

    public void DeleteButtonClicked() {
        if (SelectedUser == null) {
            WarningText.setText("No data selected");
            WarningText.setStyle("-fx-text-fill: red;");
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
                        WarningText.setStyle("-fx-text-fill: green;");
                        DisplayUserId.clear();
                        DisplayFirstName.clear();
                        DisplayLastName.clear();
                        DisplayEmail.clear();
                        DisplayPassword.clear();
                        SelectedUser = null;
                    });
                } catch (NumberFormatException e) {
                    Platform.runLater(() -> WarningText.setText("ID không hợp lệ"));
                    WarningText.setStyle("-fx-text-fill: red;");
                    System.err.println("ID không hợp lệ: " + e.getMessage());
                }
                return null;
            }
        };

        new Thread(task).start();

        WarningText.setText("Delete user ...");
        WarningText.setStyle("-fx-text-fill: red;");
    }

    public void ChangeButtonClicked() {
        if (SelectedUser == null) {
            WarningText.setText("No data selected");
            WarningText.setStyle("-fx-text-fill: red;");
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
                        WarningText.setStyle("-fx-text-fill: green;");
                    });
                } catch (NumberFormatException e) {
                    Platform.runLater(() -> WarningText.setText("ID không hợp lệ"));
                    WarningText.setStyle("-fx-text-fill: red;");
                    System.err.println("ID không hợp lệ: " + e.getMessage());
                } catch (SQLException e) {
                    Platform.runLater(() -> WarningText.setText("Lỗi mysql: " + e.getMessage()));
                    WarningText.setStyle("-fx-text-fill: red;");
                    System.out.println("Lỗi mysql: " + e.getMessage());
                }
                return null;
            }
        };

        new Thread(task).start();

        WarningText.setText("Updating user information...");
        WarningText.setStyle("-fx-text-fill: red;");
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
        if(!SortDirection.isSelected()) {
            SortDirection.setSelected(true);
            if (SortDirection.getText().equals("ASC")) SortDirection.setText("DESC");
            else SortDirection.setText("ASC");
        }
        if (SortDirection.getText().equals("ASC")) {
            if (SortByIdButton.isSelected()) UserSort.SortByIdAsc(UserList);
            if (SortByNameButton.isSelected()) UserSort.SortByNameAsc(UserList);
            if (SortByEmailButton.isSelected()) UserSort.SortByEmailAsc(UserList);
        } else {
            if (SortByIdButton.isSelected()) UserSort.SortByIdDesc(UserList);
            if (SortByNameButton.isSelected()) UserSort.SortByNameDesc(UserList);
            if (SortByEmailButton.isSelected()) UserSort.SortByEmailDesc(UserList);
        }
        ReloadDisplayList();
    }

    private void ReloadDisplayList() {
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
