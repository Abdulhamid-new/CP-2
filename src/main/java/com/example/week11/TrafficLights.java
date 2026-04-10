package com.example.week11;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TrafficLights extends Application {
    @Override
    public void start(Stage primaryStage) {
        Circle redLight = new Circle(30);
        redLight.setFill(Color.RED);
        redLight.setStroke(Color.DARKGRAY);
        redLight.setStrokeWidth(2);
        redLight.setOpacity(1.0); // Active

        Circle yellowLight = new Circle(30);
        yellowLight.setFill(Color.YELLOW);
        yellowLight.setStroke(Color.DARKGRAY);
        yellowLight.setStrokeWidth(2);
        yellowLight.setOpacity(0.3); // Dim

        Circle greenLight = new Circle(30);
        greenLight.setFill(Color.GREEN);
        greenLight.setStroke(Color.DARKGRAY);
        greenLight.setStrokeWidth(2);
        greenLight.setOpacity(0.3); // Dim

        Text stopText = new Text("Stop");
        stopText.setFont(Font.font("System", FontWeight.BOLD, 18));

        VBox vbox = new VBox(5);
        vbox.setStyle("-fx-padding: 20; -fx-alignment: center;");
        vbox.getChildren().addAll(redLight, yellowLight, greenLight, stopText);

        Scene scene = new Scene(vbox, 200, 300);
        scene.setFill(Color.DARKGRAY);

        primaryStage.setTitle("Traffic Light");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}