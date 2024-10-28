module project.libraryserver {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens project.libraryserver to javafx.fxml;
    exports project.libraryserver;
}