module project.englishapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.desktop;
    requires jdk.compiler;
    requires java.sql;

    opens project.englishapp to javafx.fxml;
    opens project.englishapp.Controllers to javafx.fxml;
    exports project.englishapp;
    exports project.englishapp.Controllers;
}