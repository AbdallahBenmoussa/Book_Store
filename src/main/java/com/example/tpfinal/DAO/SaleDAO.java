// SaleDAO.java
package com.example.tpfinal.DAO;

import com.example.tpfinal.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaleDAO {

    public boolean recordSale(long bookId, int quantitySold, double total) {
        String sql = "INSERT INTO sales (book_id, quantity_sold, total) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, bookId);
            pstmt.setInt(2, quantitySold);
            pstmt.setDouble(3, total);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}