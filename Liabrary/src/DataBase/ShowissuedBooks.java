package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowissuedBooks {
    public static void showIssudeBooks(){
    try(Connection connection = DBConnection.getconnection()){
        String query = "select * from issued_books";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String name = resultSet.getString("issued_to");
            int bookId  = resultSet.getInt("book_id");
            String issuedDate = resultSet.getString("issue_date");
            String bookName = resultSet.getString("book_name");
            System.out.println("Name: "+name);
            System.out.println("Book ID: "+bookId);
            System.out.println("Book Name: "+bookName);
            System.out.println("Issued Date: "+issuedDate);
        }
    }catch(SQLException e){
        e.getMessage();
    }
    }
}
