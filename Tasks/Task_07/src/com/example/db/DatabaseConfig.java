package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/company_db";

    private static final String USER = "root";
    private static final String PASSWORD = "Abcd1234@";

    private static final String DB_CHOICE = "MYSQL"; // Change to "POSTGRESQL" if needed

    public static Connection getConnection() throws SQLException {
        String url;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // ✅ Force load the JDBC driver
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }

        if ("MYSQL".equalsIgnoreCase(DB_CHOICE)) {
            url = MYSQL_URL;
        } else {
            throw new SQLException("Invalid DB_CHOICE specified in DatabaseConfig.");
        }
        return DriverManager.getConnection(url, USER, PASSWORD);
    }
    
}