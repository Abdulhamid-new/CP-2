package com.example.week13;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.text.DecimalFormat;

public class ExpenseTracker extends Application {
    private TextField categoryField, amountField, noteField;
    private TextArea summaryArea;
    private Label statusLabel;
    private static final String FILE_NAME = "expenses.txt";
    private DecimalFormat df = new DecimalFormat("0.00");


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create form
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(12);
        formGrid.setPadding(new Insets(20));

        Label categoryLabel = new Label("Category:");
        Label amountLabel = new Label("Amount:");
        Label noteLabel = new Label("Note:");

        categoryField = new TextField();
        amountField = new TextField();
        noteField = new TextField();

        formGrid.add(categoryLabel, 0, 0);
        formGrid.add(categoryField, 1, 0);
        formGrid.add(amountLabel, 0, 1);
        formGrid.add(amountField, 1, 1);
        formGrid.add(noteLabel, 0, 2);
        formGrid.add(noteField, 1, 2);

        // Buttons
        Button addButton = new Button("Add Expense");
        Button summaryButton = new Button("Show Summary");
        Button clearButton = new Button("Clear Fields");

        addButton.setOnAction(e -> addExpense());
        summaryButton.setOnAction(e -> showSummary());
        clearButton.setOnAction(e -> clearFields());

        // Status label
        statusLabel = new Label();

        // Summary area
        summaryArea = new TextArea();
        summaryArea.setEditable(false);
        summaryArea.setPrefHeight(200);

        // Arrange everything in VBox
        VBox root = new VBox(12);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(formGrid, addButton, summaryButton, clearButton, statusLabel, summaryArea);

        Scene scene = new Scene(root, 460, 520);
        primaryStage.setTitle("Expense Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addExpense() {
        String category = categoryField.getText().trim();
        String amountStr = amountField.getText().trim();
        String note = noteField.getText().trim();

        if (category.isEmpty() || amountStr.isEmpty()) {
            statusLabel.setText("Error: Category and Amount are required!");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                bw.write(category + "|" + amount + "|" + note);
                bw.newLine();
            }

            statusLabel.setText("Saved!");
            statusLabel.setStyle("-fx-text-fill: green;");
            clearFields();

        } catch (NumberFormatException e) {
            statusLabel.setText("Error: Amount must be a valid number!");
            statusLabel.setStyle("-fx-text-fill: red;");
        } catch (IOException e) {
            statusLabel.setText("Error: Could not save to file!");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    private void showSummary() {
        StringBuilder summary = new StringBuilder();
        double total = 0.0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
    String[] parts = line.split("\\|");
                if (parts.length >= 2) {
        String category = parts[0];
        double amount = Double.parseDouble(parts[1]);
        String note = parts.length > 2 ? parts[2] : "";

        total += amount;
        String displayNote = note.isEmpty() ? "" : " (" + note + ")";
        summary.append(String.format("%s --- $%.2f%s\n", category, amount, displayNote));
    }
}

            summary.append(String.format("\nTotal: $%.2f", total));
        summaryArea.setText(summary.toString());

        } catch (FileNotFoundException e) {
        summaryArea.setText("No expenses recorded yet.");
        } catch (IOException e) {
        summaryArea.setText("Error reading file.");
        }
                }

private void clearFields() {
    categoryField.clear();
    amountField.clear();
    noteField.clear();
}
}