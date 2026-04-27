package com.example.week13;

import java.sql.*;
import java.util.Scanner;

public class AuthorsBrowser {
    public static void main(String[] args) {

        String queryAll = "SELECT * FROM Authors";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(queryAll)) {

            System.out.println("All authors:");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("AuthorID") + " | " +
                        rs.getString("FirstName") + " " +
                        rs.getString("LastName"));
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter last-name prefix to search: ");
        String prefix = scanner.nextLine();

        String searchQuery = "SELECT * FROM Authors WHERE LastName LIKE ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(searchQuery)) {

            ps.setString(1, prefix + "%");
            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println("ID: " + rs.getInt("AuthorID") + " | " +
                        rs.getString("FirstName") + " " +
                        rs.getString("LastName"));
            }

            if (!found) {
                System.out.println("No results found.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
