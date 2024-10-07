module project.libraryserver {
    requires javafx.controls;
    requires javafx.fxml;


    opens project.libraryserver to javafx.fxml;
    exports project.libraryserver;
}