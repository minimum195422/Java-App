module app.english_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.english_app.Controllers to javafx.fxml;
    exports app.english_app;
}