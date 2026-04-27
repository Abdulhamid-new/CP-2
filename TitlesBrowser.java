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

public class TitlesBrowser extends Application {

    private TableView<AuthorBook> table = new TableView<>();
    private TextField searchField = new TextField();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        TableColumn<AuthorBook, String> colFirst = new TableColumn<>("First Name");
        colFirst.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<AuthorBook, String> colLast = new TableColumn<>("Last Name");
        colLast.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<AuthorBook, String> colISBN = new TableColumn<>("ISBN");
        colISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        TableColumn<AuthorBook, String> colTitle = new TableColumn<>("Title");
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colTitle.setPrefWidth(230);

        table.getColumns().addAll(colFirst, colLast, colISBN, colTitle);

        Button searchBtn = new Button("Search");
        searchBtn.setOnAction(e -> {
            String prefix = searchField.getText();
            loadData(prefix.isEmpty() ? "%" : prefix + "%");
        });

        VBox root = new VBox(10, searchField, searchBtn, table);
        root.setStyle("-fx-padding: 15;");

        loadData("%");

        stage.setTitle("Titles Browser");
        stage.setScene(new Scene(root, 640, 400));
        stage.show();
    }

    private void loadData(String pattern) {

        List<AuthorBook> list = new ArrayList<>();

        String sql = """
                SELECT a.FirstName, a.LastName, t.ISBN, t.Title
                FROM Authors a
                INNER JOIN AuthorISBN ai ON a.AuthorID = ai.AuthorID
                INNER JOIN Titles t ON ai.ISBN = t.ISBN
                WHERE a.LastName LIKE ?
                ORDER BY a.LastName, a.FirstName
                """;

        try (Connection c = DatabaseUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, pattern);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new AuthorBook(
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("ISBN"),
                        rs.getString("Title")
                ));
            }

            table.getItems().setAll(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
