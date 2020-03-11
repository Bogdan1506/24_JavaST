package by.avdeev.pizzeria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Runner {
    public static void main(String[] args) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
        while(resultSet.next()) {
            System.out.println(resultSet.getString("login"));
        }
    }
}
//jdbc:mysql://localhost:3306/JDBCDemo", "root", "password