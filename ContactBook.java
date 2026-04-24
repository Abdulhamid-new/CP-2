package com.example.week13;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactBook extends Application {
    private Stage primaryStage;
    private ListView<String> contactListView;
    private ObservableList<String> contactItems;
    private Label statusLabel;
    private static final String FILE_NAME = "contacts.txt";

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showContactListScene();
    }

    private void showContactListScene() {
        contactListView = new ListView<>();
        contactItems = FXCollections.observableArrayList();
        contactListView.setItems(contactItems);

        statusLabel = new Label();

        loadContacts();

        Button addButton = new Button("Add New");
        Button deleteButton = new Button("Delete Selected");
        Button refreshButton = new Button("Refresh");

        addButton.setOnAction(e -> showAddContactScene());
        deleteButton.setOnAction(e -> deleteSelectedContact());
        refreshButton.setOnAction(e -> loadContacts());

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        Label titleLabel = new Label("Contact List");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        root.getChildren().addAll(
                titleLabel,
                contactListView,
                addButton,
                deleteButton,
                refreshButton,
                statusLabel
        );

        Scene scene = new Scene(root, 480, 380);
        primaryStage.setTitle("Contact Book - Contacts");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAddContactScene() {
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(12);
        formGrid.setPadding(new Insets(20));
        formGrid.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Name:");
        Label phoneLabel = new Label("Phone:");
        Label emailLabel = new Label("Email:");

        TextField nameField = new TextField();
        TextField phoneField = new TextField();
        TextField emailField = new TextField();

        formGrid.add(nameLabel, 0, 0);
        formGrid.add(nameField, 1, 0);
        formGrid.add(phoneLabel, 0, 1);
        formGrid.add(phoneField, 1, 1);
        formGrid.add(emailLabel, 0, 2);
        formGrid.add(emailField, 1, 2);

        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        Label formStatusLabel = new Label();

        saveButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                formStatusLabel.setText("All fields are required!");
                formStatusLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            saveContact(name, phone, email);
            loadContacts();
            showContactListScene();
        });

        cancelButton.setOnAction(e -> showContactListScene());

        VBox root = new VBox(16);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(formGrid, saveButton, cancelButton, formStatusLabel);
        Scene scene = new Scene(root, 480, 380);
        primaryStage.setTitle("Contact Book - Add Contact");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadContacts() {
        contactItems.clear();
        List<String> contacts = readAllContacts();

        if (contacts.isEmpty()) {
            contactItems.add("No contacts found. Click 'Add New' to create one.");
        } else {
            for (String contact : contacts) {
                String[] parts = contact.split("\\|");
                if (parts.length == 3) {
                    contactItems.add(String.format("%s --- %s@%s", parts[0], parts[1], parts[2]));
                }
            }
        }

        statusLabel.setText("");
    }

    private List<String> readAllContacts() {
        List<String> contacts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    contacts.add(line);
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            showStatus("Error reading contacts file!", true);
        }
        return contacts;
    }

    private void saveContact(String name, String phone, String email) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(name + "|" + phone + "|" + email);
            bw.newLine();
            showStatus("Contact saved successfully!", false);
        } catch (IOException e) {
            showStatus("Error saving contact!", true);
        }
    }

    private void deleteSelectedContact() {
        String selected = contactListView.getSelectionModel().getSelectedItem();
        if (selected == null || selected.equals("No contacts found. Click 'Add New' to create one.")) {
            showStatus("Please select a contact to delete!", true);
            return;
        }

        String name = selected.split(" --- ")[0];

        List<String> allContacts = readAllContacts();
        boolean found = false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String contact : allContacts) {
                String[] parts = contact.split("\\|");
                if (parts.length == 3 && !parts[0].equals(name)) {
                    bw.write(contact);
                    bw.newLine();
                } else if (parts.length == 3 && parts[0].equals(name)) {
                    found = true;
                }
            }
            if (found) {
                showStatus("Contact deleted successfully!", false);
                loadContacts();
            } else {
                showStatus("Contact not found!", true);
            }
        } catch (IOException e) {
            showStatus("Error deleting contact!", true);
        }
    }

    private void showStatus(String message, boolean isError) {
        statusLabel.setText(message);
        statusLabel.setStyle(isError ? "-fx-text-fill: red;" : "-fx-text-fill: green;");

        new Thread(() -> {
            try {
                Thread.sleep(3000);
                javafx.application.Platform.runLater(() -> statusLabel.setText(""));
            } catch (InterruptedException e) {
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}