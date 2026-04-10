package com.example.week11;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Problem6 extends Application {

    @Override
    public void start(Stage stage) {

        double R = 0.23;
        double G = 0.68;
        double B = 0.80;

        Color color = new Color(R, G, B, 1);
        Rectangle rect = new Rectangle(200, 200);
        rect.setFill(color);

        Text rText = new Text(String.format("R: %.2f", R));
        Text gText = new Text(String.format("G: %.2f", G));
        Text bText = new Text(String.format("B: %.2f", B));

        String hex = String.format("#%02X%02X%02X",
                (int)(R * 255),
                (int)(G * 255),
                (int)(B * 255));
        Text hexText = new Text(hex);
        hexText.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        VBox root = new VBox(8, rect, rText, gText, bText, hexText);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 300, 350);

        stage.setTitle("Color Mixer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}