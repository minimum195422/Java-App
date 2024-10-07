module project.libraryclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens project.libraryclient to javafx.fxml;
    exports project.libraryclient;
}