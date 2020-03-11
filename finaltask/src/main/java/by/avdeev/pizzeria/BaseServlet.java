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

public class BaseServlet extends HttpServlet {
    private Connection connection;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
        String command = req.getParameter("command");
        if (command == null) {
            command = "list";
        }
        switch (command) {
            case "list":
                getUserList(req, resp);
                break;
            case "update":
                updateUser(req, resp);
                break;
            case "add":
                signUp(req, resp);
                break;
            case "login":
                signIn(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
        }
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO(connection);
        int id = Integer.parseInt(req.getParameter("id"));
        userDAO.delete(id);
        getUserList(req, resp);
    }

    public void signIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO(connection);
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        List<User> users = null;
        try {
            users = userDAO.findAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        for (User user : users) {
            if (login.equals(user.getLogin())) {
                if (password.equals(user.getPassword())) {
                    getUserList(req, resp);
                    return;
                }
            }
        }
        req.getRequestDispatcher("sign-up.jsp").forward(req, resp);
    }

    public void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO(connection);
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = new User(login, password, 3);
        System.out.println(user);
        userDAO.create(user);
        getUserList(req, resp);
    }

    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO(connection);
        String id = req.getParameter("id").trim();
        int userId = Integer.parseInt(id);
        User user = userDAO.findEntityById(userId);
        System.out.println(user);
        int role = Integer.parseInt(req.getParameter("role"));
        user.setRole(role == 1 ? 2 : (role == 2 ? 3 : (role == 3 ? 1 : 0)));
        System.out.println(user);
        userDAO.update(user);
        getUserList(req, resp);
    }

    public void getUserList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO(connection);
        List<User> users = null;
        try {
            users = userDAO.findAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("users", users);  //todo
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }
}
