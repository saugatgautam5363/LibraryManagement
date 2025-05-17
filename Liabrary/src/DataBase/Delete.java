package DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    public static void delete(int bookId) {
    try(Connection connection = DBConnection.getconnection()) {
        String query1 = "DELETE FROM issued_books WHERE book_id = ?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
        preparedStatement1.setInt(1,bookId);
        preparedStatement1.executeUpdate();
        String query = "DELETE FROM books WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,bookId);
        int row = preparedStatement.executeUpdate();
        if(row>0){
            System.out.println("Delete successfully!!");
        }else {
            System.out.println("Books is not deletes!!");
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
}
}
