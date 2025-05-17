package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SearchBooks {
    public static void searchBooks(String title){

    try(Connection connection = DBConnection.getconnection()){
        String query = "select * from books WHERE title LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,title);
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    }
}
