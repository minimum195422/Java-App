module project.libraryserver {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.json;
    requires jbcrypt;

    opens project.libraryserver to javafx.fxml;
    opens project.libraryserver.Controllers.DashBoard to javafx.fxml;
    opens project.libraryserver.Controllers.Login to javafx.fxml;
    opens project.libraryserver.Controllers.Register to javafx.fxml;
    opens project.libraryserver.Controllers.Card to javafx.fxml;
    opens project.libraryserver.Controllers.DashBoard.Content to javafx.fxml;

    exports project.libraryserver;
    exports project.libraryserver.Controllers.DashBoard;
    exports project.libraryserver.Controllers.Login;
    exports project.libraryserver.Controllers.Register;
    exports project.libraryserver.Controllers.Card;
    exports project.libraryserver.Controllers.DashBoard.Content;
}