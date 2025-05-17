package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookInsertDB {

    public static void Insert(int id,String bookName, String author, int quantity) {
        String query = "INSERT INTO books(id,title, author, quantity) VALUES (?, ?, ?,?)";

        try (Connection connection = DBConnection.getconnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1,id);
                preparedStatement.setString(2, bookName);
                preparedStatement.setString(3, author);
                preparedStatement.setInt(4, quantity);

                int row = preparedStatement.executeUpdate();
                if (row > 0) {
                    System.out.println("Book Inserted Successfully!!");
                } else {
                    System.out.println("Book Not Inserted!!");
                }

            } catch (SQLException e) {
                System.out.println("Error preparing or executing statement: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
}
