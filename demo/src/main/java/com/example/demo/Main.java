package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.demo.library.SceneHandler;

import java.io.IOException;

public class Main extends Application {

    SceneHandler sceneHandler;
    Scene LoginPage, RegisterPage;
    @Override
    public void start(Stage stage) throws IOException {
        sceneHandler = SceneHandler.getInstance(Main.class, stage);
        LoginPage = sceneHandler.addScene("LoginPage", "Login.fxml");
        RegisterPage = sceneHandler.addScene("RegisterPage", "Register.fxml");
        sceneHandler.setScene("LoginPage");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}