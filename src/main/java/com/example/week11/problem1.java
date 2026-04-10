package com.example.week11;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class problem1 extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("Hello");

        Scene scene = new Scene(label, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Test");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}