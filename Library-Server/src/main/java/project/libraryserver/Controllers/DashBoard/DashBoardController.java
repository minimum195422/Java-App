package project.libraryserver.Controllers.DashBoard;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import project.libraryserver.Consts.DATA;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {

    public ToggleGroup function_button_toggle_group;
    public ToggleButton AddNewButton;
    public ToggleButton ServerlLog;
    public ToggleButton ManageUsersButton;
    public ToggleButton BorrowReturnDocumentButton;
    public ToggleButton LoginLogoutButton;
    public ImageView ExitButton;
    public ImageView VersionInformationIcon;
    public BorderPane ContentDisplay;





    public void ManageUsersOnClicked(MouseEvent mouseEvent) {
        LoadPage(DATA.MANAGE_USERS_LINK);
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
        // liet ke cac button roi set tooglegroup
        ServerlLog.setToggleGroup(function_button_toggle_group);


        // cai nut duoc hien thi dau tien khi chay
        ServerlLog.setSelected(true);

        // cai content page tuong ung voi nut duoc chon
        LoadPage(DATA.SERVER_LOG_LINK);
    }

    // ServerLog button handler
    public void ServerLogOnClicked(MouseEvent mouseEvent) {
        LoadPage(DATA.SERVER_LOG_LINK);
    }

    public void LoginLogoutOnClicked(MouseEvent mouseEvent) {
        LoadPage(DATA.LOGIN_LOGOUT_LINK);
    }

    public void AddNewBookOnClicked(MouseEvent mouseEvent) {
        LoadPage(DATA.ADD_NEW_BOOK_LINK);
    }

    public void ManageDocumentOnClicked(MouseEvent mouseEvent) {
        LoadPage(DATA.MANAGE_DOCUMENT_LINK);
    }
}
