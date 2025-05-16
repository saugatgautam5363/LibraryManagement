package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IssuedBook {
   public static void Issued_books(int bookId,String isIssued,String bookName){
       try(Connection connection = DBConnection.getconnection()){
           String Update = "UPDATE books SET issued = true WHERE id = ?";
           PreparedStatement preparedStatement = connection.prepareStatement(Update);
           preparedStatement.setInt(1,bookId);
           preparedStatement.executeUpdate();
           String insert = "INSERT INTO issued_books(book_id,issued_to,book_name,issue_date) VALUES (?,?,?, CURDATE())";
           PreparedStatement preparedStatement1 = connection.prepareStatement(insert);
           preparedStatement1.setInt(1,bookId);
           preparedStatement1.setString(2,isIssued);
           preparedStatement1.setString(3,bookName);
           preparedStatement1.executeUpdate();
           System.out.println("Books issued Successfully!!");
       }catch (SQLException e){
           System.out.println(e.getMessage());
       }
   }

}
