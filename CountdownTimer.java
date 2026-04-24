package com.example.week13;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CountdownTimer extends Application {
    private TextField minutesField;
    private Label timeLabel, statusLabel;
    private Button startButton, pauseResumeButton, resetButton;
    private Timeline timeline;
    private int remainingSeconds;
    private boolean isRunning = false;
    private FadeTransition fadeTransition;

    @Override
    public void start(Stage primaryStage) {
        minutesField = new TextField();
        minutesField.setPromptText("Enter minutes");
        minutesField.setMaxWidth(150);

        timeLabel = new Label("00:00");
        timeLabel.setStyle("-fx-font-size: 52px; -fx-font-weight: bold; -fx-font-family: monospace;");

        startButton = new Button("Start");
        pauseResumeButton = new Button("Pause");
        resetButton = new Button("Reset");

        startButton.setOnAction(e -> startTimer());
        pauseResumeButton.setOnAction(e -> pauseResumeTimer());
        resetButton.setOnAction(e -> resetTimer());

        pauseResumeButton.setDisable(true);
        resetButton.setDisable(true);

        statusLabel = new Label();

        VBox root = new VBox(16);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(minutesField, startButton, timeLabel, pauseResumeButton, resetButton, statusLabel);

        Scene scene = new Scene(root, 340, 320);
        primaryStage.setTitle("Countdown Timer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startTimer() {
        try {
            int minutes = Integer.parseInt(minutesField.getText().trim());
            if (minutes <= 0) {
                showError("Please enter a positive number of minutes!");
                return;
            }

            remainingSeconds = minutes * 60;
            updateTimeDisplay();

            // Stop any existing timeline
            if (timeline != null) {
                timeline.stop();
            }

            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();

            isRunning = true;
            startButton.setDisable(true);
            pauseResumeButton.setDisable(false);
            resetButton.setDisable(false);
            pauseResumeButton.setText("Pause");
            statusLabel.setText("");
            timeLabel.setStyle("-fx-font-size: 52px; -fx-font-weight: bold; -fx-font-family: monospace; -fx-text-fill: black;");

            if (fadeTransition != null) {
                fadeTransition.stop();
                timeLabel.setOpacity(1.0);
            }

        } catch (NumberFormatException e) {
            showError("Please enter a valid number!");
        }
    }

    private void updateTimer() {
        if (remainingSeconds > 0) {
            remainingSeconds--;
            updateTimeDisplay();
        }

        if (remainingSeconds == 0) {
            timeline.stop();
            isRunning = false;
            startButton.setDisable(false);
            pauseResumeButton.setDisable(true);
            resetButton.setDisable(false);
statusLabel.setText("Time's up!");
            statusLabel.setStyle("-fx-text-fill: red;");

            timeLabel.setStyle("-fx-font-size: 52px; -fx-font-weight: bold; -fx-font-family: monospace; -fx-text-fill: red;");
fadeTransition = new FadeTransition(Duration.seconds(0.5), timeLabel);
        fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.1);
            fadeTransition.setAutoReverse(true);
            fadeTransition.setCycleCount(Animation.INDEFINITE);
            fadeTransition.play();
        }
                }

private void updateTimeDisplay() {
    int minutes = remainingSeconds / 60;
    int seconds = remainingSeconds % 60;
    timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
}

private void pauseResumeTimer() {
    if (timeline != null) {
        if (timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.pause();
            pauseResumeButton.setText("Resume");
            statusLabel.setText("Paused");
        } else if (timeline.getStatus() == Animation.Status.PAUSED) {
            timeline.play();
            pauseResumeButton.setText("Pause");
            statusLabel.setText("");
        }
    }
}

private void resetTimer() {
    if (timeline != null) {
        timeline.stop();
    }
    if (fadeTransition != null) {
        fadeTransition.stop();
        timeLabel.setOpacity(1.0);
    }

    remainingSeconds = 0;
    updateTimeDisplay();
    minutesField.clear();
    startButton.setDisable(false);
    pauseResumeButton.setDisable(true);
    resetButton.setDisable(true);
    pauseResumeButton.setText("Pause");
    statusLabel.setText("");
    timeLabel.setStyle("-fx-font-size: 52px; -fx-font-weight: bold; -fx-font-family: monospace; -fx-text-fill: black;");
    isRunning = false;
}

private void showError(String message) {
    statusLabel.setText(message);
    statusLabel.setStyle("-fx-text-fill: red;");
}

public static void main(String[] args) {
    launch(args);
}
}