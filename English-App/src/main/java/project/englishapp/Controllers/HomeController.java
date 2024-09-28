package project.englishapp.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;

public class HomeController {
    @FXML
    public ScrollPane Home_HomeScroll;

    public void Home_HomeScroll(ScrollEvent scrollEvent) {
        double y_pos = scrollEvent.getDeltaY();
        double height = Home_HomeScroll.getContent().getBoundsInLocal().getHeight();
        Home_HomeScroll.setVvalue(Home_HomeScroll.getVvalue() - y_pos/height);
    }
}
