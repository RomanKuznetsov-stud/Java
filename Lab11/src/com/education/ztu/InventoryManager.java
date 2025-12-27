package com.education.ztu;
import java.sql.*;

public class InventoryManager {

    public static void runTasks() {
        try (Connection conn = DatabaseConnector.getConnection()) {

            System.out.println("\n--- Task 3: Statement & Batch ---");
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE IF EXISTS products");
            stmt.execute("CREATE TABLE products (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), category VARCHAR(50), brand VARCHAR(50), price DOUBLE)");

            for (int i = 1; i <= 10; i++) {
                stmt.addBatch("INSERT INTO products (name, category, brand, price) VALUES ('Product" + i + "', 'Electronics', 'BrandA', " + (100 * i) + ")");
            }
            stmt.executeBatch();

            ResultSet rs = stmt.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }

            System.out.println("\n--- Task 4: PreparedStatement ---");
            String insertSql = "INSERT INTO products (name, category, brand, price) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            for (int i = 11; i <= 15; i++) {
                pstmt.setString(1, "PreItem" + i);
                pstmt.setString(2, "Home");
                pstmt.setString(3, "BrandB");
                pstmt.setDouble(4, 50.0 * i);
                pstmt.executeUpdate();
            }

            PreparedStatement filterStmt = conn.prepareStatement("SELECT * FROM products WHERE brand = ?");
            filterStmt.setString(1, "BrandB");
            ResultSet filteredRs = filterStmt.executeQuery();
            while (filteredRs.next()) {
                System.out.println("Filtered: " + filteredRs.getString("name"));
            }

            stmt.executeUpdate("DELETE FROM products");
            System.out.println("All records deleted.");

            System.out.println("\n--- Task 5: Transactions & Savepoints ---");
            conn.setAutoCommit(false);
            Savepoint sp = null;
            try {
                stmt.executeUpdate("INSERT INTO products (name, price) VALUES ('TransactionItem1', 500)");
                sp = conn.setSavepoint("Point1");
                System.out.println("Added Item 1, Savepoint set.");

                stmt.executeUpdate("INSERT INTO products (name, WRONG_COL) VALUES ('ErrorItem', 0)");
                conn.commit();
            } catch (SQLException e) {
                System.out.println("Error detected: " + e.getMessage());
                if (sp != null) {
                    conn.rollback(sp);
                    conn.commit();
                    System.out.println("Rollback to Savepoint successful.");
                }
            }
            conn.setAutoCommit(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}