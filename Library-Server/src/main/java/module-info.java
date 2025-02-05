module project.libraryserver {
    requires javafx.fxml;
    requires java.sql;
    requires org.json;
    requires jbcrypt;
    requires javafx.web;
    requires java.desktop;
    requires mysql.connector.j;

    opens project.libraryserver to javafx.fxml;
    opens project.libraryserver.Controllers.Card to javafx.fxml;
    opens project.libraryserver.Controllers.DashBoard to javafx.fxml;
    opens project.libraryserver.Controllers.DashBoard.Content to javafx.fxml;
    opens project.libraryserver.Book to javafx.fxml;

    exports project.libraryserver;
    exports project.libraryserver.Controllers.Card;
    exports project.libraryserver.Controllers.DashBoard;
    exports project.libraryserver.Controllers.DashBoard.Content;
    exports project.libraryserver.Book;
}