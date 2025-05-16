package DataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Return {
    public static void returnBook(int bookId){
        try (Connection connection = DBConnection.getconnection()){
            String query = "UPDATE books SET issued = false WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,bookId);
            preparedStatement.executeUpdate();

            String query1 = "UPDATE issued_books SET return_date = ? WHERE book_id = ? AND return_data IS NULL";
            PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
            preparedStatement1.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatement1.setInt(2,bookId);
            int rows = preparedStatement.executeUpdate();
            if(rows>0){
                System.out.println("Return is successfully");
            }else {
                System.out.println("Book is not Return!!");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
