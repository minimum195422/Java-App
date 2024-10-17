package project.libraryserver;

import javafx.application.Application;
import javafx.stage.Stage;

import java.net.ServerSocket;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ServerSocket serverSocket = new ServerSocket(2910);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
