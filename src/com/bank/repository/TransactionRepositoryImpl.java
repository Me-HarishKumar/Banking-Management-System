package com.bank.repository;

import com.bank.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {

    @Override
    public void save(Transaction transaction) {
        String sql = "INSERT INTO transactions(transaction_id, account_number, amount, type, timestamp, description) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, transaction.getTransactionId());
            pstmt.setString(2, transaction.getAccountNumber());
            pstmt.setDouble(3, transaction.getAmount());
            pstmt.setString(4, transaction.getType().name());
            pstmt.setString(5, transaction.getTimestamp().toString());
            pstmt.setString(6, transaction.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction> findByAccountNumber(String accountNumber) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE account_number = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Transaction t = new Transaction(
                    rs.getString("transaction_id"),
                    rs.getString("account_number"),
                    rs.getDouble("amount"),
                    Transaction.TransactionType.valueOf(rs.getString("type")),
                    LocalDateTime.parse(rs.getString("timestamp")),
                    rs.getString("description")
                );
                transactions.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
