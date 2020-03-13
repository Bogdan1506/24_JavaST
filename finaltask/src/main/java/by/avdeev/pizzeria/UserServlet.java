package by.avdeev.pizzeria;

import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.UserDAO;
import by.avdeev.pizzeria.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet {
    private UserDAO userDAO;

    public UserServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userDAO.delete(id);
        getUserList(req, resp);
    }

    public void signIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = new User(login, password, 3);
        userDAO.create(user);
        getUserList(req, resp);
    }

    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int userId = Integer.parseInt(id);
        User user = userDAO.findEntityById(userId);
        user.setPassword(req.getParameter("password"));
        user.setRole(Integer.parseInt(req.getParameter("role")));
        userDAO.update(user);
        getUserList(req, resp);
    }

    public void getUserList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
