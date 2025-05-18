package DataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Return {
    public static void returnBook(int bookId) {
        try (Connection connection = DBConnection.getconnection()) {

            // 1. Update the return_date in issued_books
            String updateIssuedQuery = "UPDATE issued_books SET return_date = ? WHERE book_id = ? AND return_date IS NULL";
            PreparedStatement psIssued = connection.prepareStatement(updateIssuedQuery);
            psIssued.setDate(1, Date.valueOf(LocalDate.now()));
            psIssued.setInt(2, bookId);
            int issuedRows = psIssued.executeUpdate();

            if (issuedRows > 0) {
                // 2. Mark book as available (increase quantity or set issued=false)
                String updateBookQuery = "UPDATE books SET issued = false WHERE id = ?";
                PreparedStatement psBook = connection.prepareStatement(updateBookQuery);
                psBook.setInt(1, bookId);
                psBook.executeUpdate();

                System.out.println("Return is successful.");
            } else {
                System.out.println("Book was not marked as issued or already returned.");
            }

        } catch (SQLException e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }
}
