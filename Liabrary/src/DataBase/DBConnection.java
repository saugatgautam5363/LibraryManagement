package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/library_db";
    private static final String username = "root";
    private static final String password = "admin";

    public static Connection getconnection(){
        try{
            return DriverManager .getConnection(url,username,password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
