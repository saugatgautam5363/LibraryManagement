package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update {
    public static void updateBooks(int bookId,String title,String author,int quantity){

    try(Connection connection = DBConnection.getconnection()){
        String query = "UPDATE books SET title = ?,author=?,quantity=? WHERE id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,title);
        preparedStatement.setString(2,author);
        preparedStatement.setInt(3,quantity);
        preparedStatement.setInt(4,bookId);
        int row = preparedStatement.executeUpdate();
        if(row>0){
            System.out.println("Books update successfully!!");
        }else {
            System.out.println("Update Failed!!");
        }
    }catch (SQLException e){
        System.out.println(e.getMessage());
    }
    }
}
