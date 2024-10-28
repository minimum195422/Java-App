module project.libraryclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.desktop;
    requires jdk.compiler;
    requires java.xml.crypto;

    opens project.libraryclient to javafx.fxml;
    opens project.libraryclient.Controllers to javafx.fxml;
    opens project.libraryclient.Controllers.DashBoardContent to javafx.fxml;

    exports project.libraryclient;
    exports project.libraryclient.Controllers;
    exports project.libraryclient.Controllers.DashBoardContent;
}