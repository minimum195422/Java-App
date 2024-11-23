package project.libraryserver.Controllers.DashBoard.Content;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerLogController implements Initializable {

    public VBox ListLog;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadlogfromlogfile();
    }

    public void loadlogfromlogfile() {
        for (int i=0; i < 30; ++i) {
            Label label = new Label();
            label.setText("log number: " + i);
            label.setPrefSize(1000, 30);
            label.setMinSize(1000, 30);
            label.setMaxSize(1000, 30);
            ListLog.getChildren().add(label);
        }
    }
}
