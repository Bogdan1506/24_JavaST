package by.avdeev.pizzeria;

import by.avdeev.pizzeria.dao.GoodsDao;
import by.avdeev.pizzeria.dao.ItemDAO;
import by.avdeev.pizzeria.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class BaseServlet extends HttpServlet {
    private UserDAO userDAO;
    private GoodsDao goodsDAO;
    private UserServlet userServlet;
    private GoodsServlet goodsServlet;
    private ItemServlet itemServlet;

    {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        userDAO = new UserDAO(connection);
        userServlet = new UserServlet(userDAO);
        goodsDAO = new GoodsDao(connection);
        goodsServlet = new GoodsServlet(goodsDAO);
        ItemDAO itemDAO = new ItemDAO(connection);
        itemServlet = new ItemServlet(itemDAO);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        if (command == null) {
            command = "list";
        }
        switch (command) {
            case "getItemList":
                itemServlet.getItemList(req, resp);
                break;
            case "createItem":
                itemServlet.createItem(req, resp);
                break;
            case "deleteItem":
                itemServlet.deleteItem(req, resp);
                break;
            case "list":
                userServlet.getUserList(req, resp);
                break;
            case "update":
                userServlet.updateUser(req, resp);
                break;
            case "add":
                userServlet.signUp(req, resp);
                break;
            case "login":
                userServlet.signIn(req, resp);
                break;
            case "delete":
                userServlet.delete(req, resp);
                break;
            case "goodsList":
                goodsServlet.getGoodsList(req, resp);
                break;
            case "updateGoods":
                goodsServlet.update(req, resp);
                break;
            case "deleteGoods":
                goodsServlet.delete(req, resp);
                break;
            case "addGoods":
                goodsServlet.create(req, resp);
                break;
        }
    }

    /*public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO(connection);  //todo move to field
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
        String id = req.getParameter("id");
        int userId = Integer.parseInt(id);
        User user = userDAO.findEntityById(userId);
        user.setPassword(req.getParameter("password"));
        user.setRole(Integer.parseInt(req.getParameter("role")));
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
        System.out.println(users);
        req.setAttribute("users", users);  //todo
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }*/
}
