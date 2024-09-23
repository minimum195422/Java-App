module project.englishapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;

    opens project.englishapp to javafx.fxml;
    opens project.englishapp.Controllers to javafx.fxml;
    exports project.englishapp;
    exports project.englishapp.Controllers;
}