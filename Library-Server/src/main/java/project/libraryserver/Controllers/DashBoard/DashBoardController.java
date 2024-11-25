package project.libraryserver.Controllers.DashBoard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import project.libraryserver.Consts.DATA;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {

    private final ToggleGroup function_button_toggle_group = new ToggleGroup();

    @FXML
    public BorderPane ContentDisplay;


    // Server Log
    @FXML
    public ToggleButton ServerLogButton;

    public void ServerLogOnClicked(MouseEvent mouseEvent) {
        LoadPage(DATA.SERVER_LOG_LINK);
    }


    // Add New Book
    @FXML
    public ToggleButton AddNewBookButton;

    public void AddNewBookOnClicked(MouseEvent mouseEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(
                    Objects.requireNonNull(
                            getClass().getResource(DATA.ADD_NEW_BOOK_LINK))
            );
            ContentDisplay.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }


    // Manage User
    @FXML
    public ToggleButton ManageUsersButton;

    public void ManageUsersOnClicked(MouseEvent mouseEvent) {
        try {
            AnchorPane pane = FXMLLoader.load(
                    Objects.requireNonNull(
                            getClass().getResource(DATA.MANAGE_USERS_LINK))
            );
            ContentDisplay.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }


    // Manage Document
    @FXML
    public ToggleButton ManageDocumentButton;

    public void ManageDocumentOnClicked() {
        try {
            AnchorPane pane = FXMLLoader.load(
                    Objects.requireNonNull(
                            getClass().getResource(DATA.MANAGE_DOCUMENT_LINK))
            );
            ContentDisplay.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private void LoadPage(String path) {
        try {
            ScrollPane pane = FXMLLoader.load(
                    Objects.requireNonNull(
                            getClass().getResource(path))
            );
            ContentDisplay.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServerLogButton.setToggleGroup(function_button_toggle_group);
        AddNewBookButton.setToggleGroup(function_button_toggle_group);
        ManageUsersButton.setToggleGroup(function_button_toggle_group);
        ManageDocumentButton.setToggleGroup(function_button_toggle_group);

        // cai nut duoc hien thi dau tien khi chay
        ServerLogButton.setSelected(true);

        // cai content page tuong ung voi nut duoc chon
        LoadPage(DATA.SERVER_LOG_LINK);
    }







}