package by.avdeev.pizzeria;

import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.UserDAO;
import by.avdeev.pizzeria.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class ButtonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        UserDAO userDAO = new UserDAO(connection);
        String id = req.getParameter("id").trim();
        int userId = Integer.parseInt(id);
        User user = userDAO.findEntityById(userId);
        System.out.println(user);
        System.out.println("role = " + req.getParameter("role"));
        int role = Integer.parseInt(req.getParameter("role"));
        user.setRole(role == 1 ? 2 : (role == 2 ? 3 : (role == 3 ? 1 : 0)));
        System.out.println(user);
        userDAO.update(user);
        List<User> users = null;
        try {
            users = userDAO.findAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("users", users);
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }
}
