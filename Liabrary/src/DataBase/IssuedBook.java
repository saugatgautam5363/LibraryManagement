package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IssuedBook {
    public static void Issued_books(int bookId, String issuedTo, String bookName) {
        try (Connection connection = DBConnection.getconnection()) {
            // Update 'books' table: mark as issued
            String updateBook = "UPDATE books SET issued = true, quantity = quantity - 1 WHERE id = ? AND quantity > 0";
            PreparedStatement psUpdate = connection.prepareStatement(updateBook);
            psUpdate.setInt(1, bookId);
            int bookRows = psUpdate.executeUpdate();

            if (bookRows == 0) {
                System.out.println("❌ Book not available or already issued!");
                return;
            }

            // Insert into 'issued_books' table
            String insertIssued = "INSERT INTO issued_books (book_id, issued_to, book_name, issue_date) VALUES (?, ?, ?, CURDATE())";
            PreparedStatement psInsert = connection.prepareStatement(insertIssued);
            psInsert.setInt(1, bookId);
            psInsert.setString(2, issuedTo);
            psInsert.setString(3, bookName);
            int issuedRows = psInsert.executeUpdate();

            if (issuedRows > 0) {
                System.out.println("✅ Book issued and recorded in database successfully.");
            } else {
                System.out.println("❌ Failed to insert issued record.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Database Error: " + e.getMessage());
        }
    }
}
