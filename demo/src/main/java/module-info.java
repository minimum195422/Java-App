module Frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens Frontend to javafx.fxml;
    opens Frontend.Class to javafx.base;
    exports Frontend;
    exports Frontend.Controller;
    opens Frontend.Controller to javafx.fxml;
}