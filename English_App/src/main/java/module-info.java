module uet.student.project.english_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens uet.student.project.english_app to javafx.fxml;
    exports uet.student.project.english_app;
}