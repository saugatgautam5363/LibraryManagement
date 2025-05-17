package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayAllBooks {
    public static void displayBooks(){
    try(Connection connection = DBConnection.getconnection()){
        String query1 = "select * from books";
        PreparedStatement preparedStatement = connection.prepareStatement(query1);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Books on Library: ");
        while (resultSet.next()){
            int  bookId =   resultSet.getInt("id");
            String title = resultSet.getString("title");
            String authorBooks = resultSet.getString("author");
            int quantity1 = resultSet.getInt("quantity");
            int issued = resultSet.getInt("issued");
            System.out.println("----------------");
            System.out.println("Book ID: "+bookId);
            System.out.println("Title: "+title);
            System.out.println("Author: "+authorBooks);
            System.out.println("Quantity: "+quantity1);
            System.out.println("Issued: "+issued);
        }
    }catch (
    SQLException e){
        System.out.println(e.getMessage());
    }
    }
}
