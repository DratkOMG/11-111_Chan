package com.example.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader root = new FXMLLoader(ClientApp.class.getResource("dcm.fxml"));
            Scene scene = new Scene(root.load());
            scene.getStylesheets().add(ClientApp.class.getResource("style.css").toExternalForm());
            primaryStage.setTitle("Exploding Dat");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
