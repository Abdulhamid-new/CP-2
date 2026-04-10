package com.example.week11;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Problem7 extends Application {
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        Label nameLabel = new Label("Abdulhamid Muxtorjonov");
        nameLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        nameLabel.setFont(Font.font("System", FontWeight.BOLD, 16));

        HBox top = new HBox(nameLabel);
        top.setPadding(new Insets(12));
        top.setStyle("-fx-background-color: #2C3E50;");
        root.setTop(top);

        GridPane center = new GridPane();
        center.setHgap(10);
        center.setVgap(10);
        center.setPadding(new Insets(20));

        center.add(new Label("Department:"), 0, 0);
        center.add(new Label("School of Computing"), 1, 0);

        center.add(new Label("Year:"), 0, 1);
        center.add(new Label("1"), 1, 1);

        center.add(new Label("GPA:"), 0, 2);
        center.add(new Label("3.7"), 1, 2);

        root.setCenter(center);

        Label bottomLabel = new Label("New Uzbekistan University");
        bottomLabel.setPadding(new Insets(8));
        bottomLabel.setAlignment(Pos.CENTER);
        bottomLabel.setMaxWidth(Double.MAX_VALUE);
        bottomLabel.setStyle("-fx-background-color: #ECF0F1; -fx-font-size: 13;");

        BorderPane.setAlignment(bottomLabel, Pos.CENTER);
        root.setBottom(bottomLabel);

        Scene scene = new Scene(root, 400, 250);
        stage.setScene(scene);
        stage.setTitle("Profile Card");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}