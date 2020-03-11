package by.avdeev.pizzeria;

import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.UserDAO;
import by.avdeev.pizzeria.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
//        Connection connection = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria?characterEncoding=latin1&useConfigs=maxPerformance&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        UserDAO userDAO = new UserDAO(connection);
        List<User> users = null;
        try {
            users = userDAO.findAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("users", users);  //todo
        req.getRequestDispatcher("user.jsp").forward(req, resp);
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria?characterEncoding=latin1&useConfigs=maxPerformance", "root", "root");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
/*        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()) {
                pw.println(resultSet.getString("id"));
                pw.println(resultSet.getString("login"));
                pw.println(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }
}
