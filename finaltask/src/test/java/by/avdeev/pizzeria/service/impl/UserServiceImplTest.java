package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.transaction.Transaction;
import by.avdeev.pizzeria.transaction.TransactionImpl;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImplTest {
    Connection connection;
    UserService userService;
    User user;

    @BeforeClass
    public void setUp() {
        try {
            user = new User("qwert22y", "123", Role.CLIENT);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria?characterEncoding=latin1&serverTimezone=UTC", "root", "root");
            connection.setAutoCommit(false);
            Transaction transaction = new TransactionImpl(connection);
            userService = new UserServiceImpl();
            userService.setTransaction(transaction);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void rollbackCon() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreate() {
        try {
            user.setId(userService.create(user));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test(dependsOnMethods = {"testCreate"})
    public void testFindAll() {
        List<User> users = null;
        try {
            users = userService.findAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        assert users != null;
        boolean isExisting = users.contains(user);
        Assert.assertTrue(isExisting);
    }

    @Test(dependsOnMethods = {"testCreate"})
    public void testFindById() {
        User resUser = null;
        try {
            resUser = userService.findById(user.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(resUser);
        Assert.assertEquals(resUser, user);
    }

    @Test(dependsOnMethods = {"testCreate"})
    public void testFindByLogin() {
        User resUser = null;
        try {
            resUser = userService.findByLogin(user.getLogin());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(resUser);
        Assert.assertEquals(resUser, user);
    }

    @Test(dependsOnMethods = {"testCreate", "testFindAll", "testFindById", "testFindByLogin", "testUpdate"})
    public void testDelete() {
//        User checkUser = null;
            boolean result = false;
        try {
            result = userService.delete(user.getId());
//            checkUser = userService.findById(user.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(result);

    }

    @Test(dependsOnMethods = {"testCreate"})
    public void testUpdate() {
        user.setPassword("456");
        User resUser = null;
        try {
            userService.update(user);
            resUser = userService.findById(user.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(resUser, user);
    }
}