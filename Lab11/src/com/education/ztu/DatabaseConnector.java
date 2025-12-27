package com.education.ztu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class DatabaseConnector {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("db");

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(
                bundle.getString("db.url"),
                bundle.getString("db.user"),
                bundle.getString("db.password")
        );
    }
}