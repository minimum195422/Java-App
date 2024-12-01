package project.libraryserver.Controllers.DashBoard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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

    public void ServerLogOnClicked() {
        try {
            ScrollPane pane = FXMLLoader.load(
                    Objects.requireNonNull(
                            getClass().getResource(DATA.SERVER_LOG_LINK))
            );
            ContentDisplay.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }


    // Add New Book
    @FXML
    public ToggleButton AddNewBookButton;

    public void AddNewBookOnClicked() {
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

    public void ManageUsersOnClicked() {
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServerLogButton.setToggleGroup(function_button_toggle_group);
        AddNewBookButton.setToggleGroup(function_button_toggle_group);
        ManageUsersButton.setToggleGroup(function_button_toggle_group);
        ManageDocumentButton.setToggleGroup(function_button_toggle_group);

        function_button_toggle_group.selectedToggleProperty().addListener(
            (_, oldToggle, newToggle) -> {
                if (newToggle == null) {
                    function_button_toggle_group.selectToggle(oldToggle);
                }
        });

        // default selected button when load page
        ServerLogButton.setSelected(true);

        // content with selected button
        try {
            ScrollPane pane = FXMLLoader.load(
                    Objects.requireNonNull(
                            getClass().getResource(DATA.SERVER_LOG_LINK))
            );
            ContentDisplay.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

}