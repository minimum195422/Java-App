module project.libraryclient {
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires jdk.compiler;
    requires java.xml.crypto;
    requires org.json;
    requires com.google.api.client.auth;
    requires google.api.client;
    requires com.google.api.client;
    requires com.google.api.client.json.gson;
    requires google.api.services.oauth2.v2.rev157;
    requires com.google.api.client.extensions.jetty.auth;
    requires com.google.api.client.extensions.java6.auth;
    requires javafx.web;
    requires com.google.gson;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires java.sql;
    requires javafx.media;
    requires java.desktop;

    opens project.libraryclient to javafx.fxml;
    opens project.libraryclient.Controllers to javafx.fxml;
    opens project.libraryclient.Controllers.DashBoard to javafx.fxml;
    opens project.libraryclient.Controllers.Login to javafx.fxml;
    opens project.libraryclient.Controllers.Register to javafx.fxml;
    opens project.libraryclient.Controllers.Card to javafx.fxml;
    opens project.libraryclient.API.GoogleAPI to javafx.fxml;

    exports project.libraryclient;
    exports project.libraryclient.Controllers.DashBoard;
    exports project.libraryclient.Controllers.Login;
    exports project.libraryclient.Controllers.Register;
    exports project.libraryclient.Controllers.Card;
    exports project.libraryclient.API.GoogleAPI;
}