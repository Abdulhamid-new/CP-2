package com.example.week14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorsManager extends Application {

    private TableView<Author> table = new TableView<>();
    private TextField firstNameField = new TextField();
    private TextField lastNameField = new TextField();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        TableColumn<Author, Integer> colID = new TableColumn<>("ID");
        colID.setCellValueFactory(new PropertyValueFactory<>("authorID"));

        TableColumn<Author, String> colFirst = new TableColumn<>("First Name");
        colFirst.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Author, String> colLast = new TableColumn<>("Last Name");
        colLast.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        table.getColumns().addAll(colID, colFirst, colLast);

        loadAuthors();

        Button addBtn = new Button("Add");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");

        addBtn.setOnAction(e -> addAuthor());
        updateBtn.setOnAction(e -> updateAuthor());
        deleteBtn.setOnAction(e -> deleteAuthor());

        HBox controls = new HBox(10, firstNameField, lastNameField, addBtn, updateBtn, deleteBtn);
        VBox root = new VBox(10, table, controls);
        root.setStyle("-fx-padding: 15;");

        stage.setTitle("Authors Manager");
        stage.setScene(new Scene(root, 550, 400));
        stage.show();
    }

    private void loadAuthors() {
        List<Author> list = new ArrayList<>();

        String sql = "SELECT * FROM Authors";

        try (Connection c = DatabaseUtil.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Author(
                        rs.getInt("AuthorID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName")
                ));
            }

            table.getItems().setAll(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addAuthor() {
        String sql = "INSERT INTO Authors (FirstName, LastName) VALUES (?, ?)";

        try (Connection c = DatabaseUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, firstNameField.getText());
            ps.setString(2, lastNameField.getText());
            ps.executeUpdate();

            loadAuthors();
            firstNameField.clear();
            lastNameField.clear();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAuthor() {
        Author selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        String sql = "UPDATE Authors SET FirstName=?, LastName=? WHERE AuthorID=?";

        try (Connection c = DatabaseUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, firstNameField.getText());
            ps.setString(2, lastNameField.getText());
            ps.setInt(3, selected.getAuthorID());

            ps.executeUpdate();
            loadAuthors();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteAuthor() {
        Author selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        String sql = "DELETE FROM Authors WHERE AuthorID=?";

        try (Connection c = DatabaseUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, selected.getAuthorID());
            ps.executeUpdate();

            loadAuthors();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
