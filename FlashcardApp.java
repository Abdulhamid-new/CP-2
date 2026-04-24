package com.example.week13;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FlashcardApp extends Application {
    private List<String[]> cards = new ArrayList<>();
    private int currentCardIndex = 0;
    private boolean showingQuestion = true;
    private Label cardLabel, indexLabel;
    private Button flipButton, nextButton, prevButton;
    private static final String FILE_NAME = "cards.txt";

    @Override
    public void start(Stage primaryStage) {
        loadCards();

        if (cards.isEmpty()) {
            Label errorLabel = new Label("No cards found. Add cards to cards.txt and restart.");
            errorLabel.setStyle("-fx-text-fill: red;");
            Scene errorScene = new Scene(errorLabel, 480, 300);
            primaryStage.setScene(errorScene);
            primaryStage.setTitle("Flashcard Quizzer - Error");
            primaryStage.show();
            return;
        }

        indexLabel = new Label();

        cardLabel = new Label();
        cardLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-wrap-text: true;");
        cardLabel.setAlignment(Pos.CENTER);
        cardLabel.setMaxWidth(400);

        flipButton = new Button("Flip");
        nextButton = new Button("Next");
        prevButton = new Button("Previous");

        flipButton.setOnAction(e -> flipCard());
        nextButton.setOnAction(e -> nextCard());
        prevButton.setOnAction(e -> previousCard());

        VBox root = new VBox(16);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(indexLabel, cardLabel, flipButton, nextButton, prevButton);

        updateDisplay();

        Scene scene = new Scene(root, 480, 300);
        primaryStage.setTitle("Flashcard Quizzer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadCards() {
        cards.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    cards.add(parts);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("cards.txt not found. Creating default cards...");
            createDefaultCards();
        } catch (IOException e) {
            System.out.println("Error reading cards.txt");
        }
    }

    private void createDefaultCards() {
        String[] defaultCards = {
                "Speed|How fast something moves (distance ÷ time)",
                "Force|A push or pull that can make things move",
                "Energy|The ability to do work or cause change",
                "Mass|How much matter is in an object (measured in kg)",
                "Weight|The force of gravity pulling on an object"
        };

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String card : defaultCards) {
                bw.write(card);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error creating default cards");
        }
        loadCards();
    }
    private void updateDisplay() {
        indexLabel.setText(String.format("Card %d / %d", currentCardIndex + 1, cards.size()));

        if (showingQuestion) {
            cardLabel.setText(cards.get(currentCardIndex)[0]);
            cardLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-wrap-text: true; -fx-background-color: lightblue; -fx-padding: 10px;");
        } else {
            cardLabel.setText(cards.get(currentCardIndex)[1]);
            cardLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-wrap-text: true; -fx-background-color: lightgreen; -fx-padding: 10px;");
        }
    }

    private void flipCard() {
        showingQuestion = !showingQuestion;
        updateDisplay();
    }

    private void nextCard() {
        currentCardIndex = (currentCardIndex + 1) % cards.size();
        showingQuestion = true;
        updateDisplay();
    }

    private void previousCard() {
        currentCardIndex = (currentCardIndex - 1 + cards.size()) % cards.size();
        showingQuestion = true;
        updateDisplay();
    }

    public static void main(String[] args) {
        launch(args);
    }
}