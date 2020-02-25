package by.avdeev.pizzeria;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Runner {
    public static void main(String[] args) throws SQLException {
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria", "root","root" );
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();
        statement.executeQuery("SELECT * FROM user");
    }
}
//jdbc:mysql://localhost:3306/JDBCDemo", "root", "password