package com.bank.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionManager {
    private static final String URL = "jdbc:sqlite:banking.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Create Customer table
            stmt.execute("CREATE TABLE IF NOT EXISTS customers (" +
                    "id TEXT PRIMARY KEY," +
                    "name TEXT NOT NULL," +
                    "email TEXT UNIQUE NOT NULL" +
                    ")");

            // Create Account table
            stmt.execute("CREATE TABLE IF NOT EXISTS accounts (" +
                    "account_number TEXT PRIMARY KEY," +
                    "customer_id TEXT NOT NULL," +
                    "balance REAL NOT NULL," +
                    "account_type TEXT NOT NULL," +
                    "FOREIGN KEY(customer_id) REFERENCES customers(id)" +
                    ")");

            // Create Transaction table
            stmt.execute("CREATE TABLE IF NOT EXISTS transactions (" +
                    "transaction_id TEXT PRIMARY KEY," +
                    "account_number TEXT NOT NULL," +
                    "amount REAL NOT NULL," +
                    "type TEXT NOT NULL," +
                    "timestamp TEXT NOT NULL," +
                    "description TEXT," +
                    "FOREIGN KEY(account_number) REFERENCES accounts(account_number)" +
                    ")");
                    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
