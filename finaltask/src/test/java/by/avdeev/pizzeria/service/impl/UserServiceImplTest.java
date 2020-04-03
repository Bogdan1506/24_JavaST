package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.pool.ConnectionPool;
import by.avdeev.pizzeria.dao.pool.ConnectionPoolImpl;
import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.ServiceFactory;
import by.avdeev.pizzeria.service.UserService;
import org.testng.Assert;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserServiceImplTest {

    @org.testng.annotations.Test
    public void testCreate() {
/*        ConnectionPool connectionPool = ConnectionPoolImpl.getConnectionPoolImpl();
        Connection connection = connectionPool.getConnection();*/
        Context ctx = null;
        Connection connection = null;
        try {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria?characterEncoding=latin1&serverTimezone=UTC", "root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ServiceFactory factory = ServiceFactory.getFactory();
        UserService userService = factory.getUserService();
        User user = new User("login", "password", Role.CLIENT);
        User userFromBD = new User();
        try {
            userService.create(user);
            Statement statement = connection.createStatement();
            String sql = "SELECT password, role FROM user WHERE login='login'";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                userFromBD.setLogin(rs.getString("login"));
                userFromBD.setPassword(rs.getString("password"));
                userFromBD.setRole(Role.getByIdentity(rs.getInt("role")));
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(user, userFromBD);
    }

    @org.testng.annotations.Test
    public void testFindAll() {
    }

    @org.testng.annotations.Test
    public void testFindById() {
    }

    @org.testng.annotations.Test
    public void testFindByLogin() {
    }

    @org.testng.annotations.Test
    public void testDelete() {
    }

    @org.testng.annotations.Test
    public void testUpdate() {
    }
}