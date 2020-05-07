package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.transaction.DAOType;
import by.avdeev.pizzeria.transaction.Transaction;
import by.avdeev.pizzeria.transaction.TransactionImpl;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.*;

public class UserServiceImplTest {
    private Connection connection;
    private UserService userService;

    @BeforeTest
    public void setUpConnection() {
        try {
            Properties properties = new Properties();
            FileInputStream inputStream = new FileInputStream("src/main/resources/connection.properties"); //TODO relative path
            properties.load(inputStream);
            String url = (String) properties.get("url");
            String username = (String) properties.get("uName");
            String password = (String) properties.get("password");
            connection = DriverManager.getConnection(
                    url, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        userService = new UserServiceImpl();
        Transaction transaction = new TransactionImpl(connection);
        userService.setTransaction(transaction);
        userService.setDAOType(DAOType.USER);
    }

    @AfterTest
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void rollback() throws SQLException {
        connection.rollback();
    }

    @DataProvider(name = "dataUserProvider")
    public Object[][] createUser() {
        User userOne = new User("test1", "1234", Role.CLIENT);
        User userTwo = new User("test2", "12345", Role.CLIENT);
        User userThree = new User("test3", "123456", Role.CLIENT);
        return new Object[][]{{userOne}, {userTwo}, {userThree}};
    }

    @Test(dataProvider = "dataUserProvider")
    public void testCreate(User expectedUser) throws ServiceException, SQLException {
        int id = userService.create(expectedUser);
        expectedUser.setId(id);
        User actualUser = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT login, password, role FROM `user` WHERE id=?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String login = rs.getString(1);
                String password = rs.getString(2);
                Role role = Role.getByIdentity(rs.getInt(3));
                actualUser = new User(id, login, password, role);
            }
        }
        assert actualUser != null;
        assertEquals(actualUser, expectedUser);
    }

    @DataProvider(name = "dataUserListProvider")
    public Object[][] createUserList() {
        User userOne = new User("test1", "1234", Role.CLIENT);
        User userTwo = new User("test2", "12345", Role.CLIENT);
        User userThree = new User("test3", "123456", Role.CLIENT);
        List<User> users = new ArrayList<>(Arrays.asList(userOne, userTwo, userThree));
        return new Object[][]{{users}};
    }

    @Test(dataProvider = "dataUserListProvider", dependsOnMethods = "testCreate")
    public void testFindAll(List<User> expectedUsers) throws ServiceException {
        for (User user : expectedUsers) {
            int id = userService.create(user);
            user.setId(id);
        }
        List<User> actualUsers = userService.findAll();
        assertEquals(actualUsers, expectedUsers);
    }

    @DataProvider(name = "dataRangeProvider")
    public Object[][] createRange() {
        User userOne = new User("test1", "1234", Role.CLIENT);
        User userTwo = new User("test2", "12345", Role.CLIENT);
        User userThree = new User("test3", "123456", Role.CLIENT);
        List<User> users = new ArrayList<>(Arrays.asList(userOne, userTwo, userThree));
        return new Object[][]{{users, 0, 20}, {users, 50, 60}, {users, -20, 0}};
    }

    @Test(dataProvider = "dataRangeProvider", dependsOnMethods = {"testCreate"})
    public void testRangeFindAll(List<User> expectedUsers, int begin, int end) throws SQLException, ServiceException {
        expectedUsers.forEach(e -> {
            try {
                e.setId(userService.create(e));
            } catch (ServiceException ex) {
                ex.printStackTrace();
            }
        });
        List<User> actualUsers = userService.findAll(begin, end);
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, login, password, role FROM user ORDER BY id LIMIT ?, ?")) {
            expectedUsers = new ArrayList<>();
            statement.setInt(1, begin);
            statement.setInt(2, end);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                int roleInt = rs.getInt("role");
                Role role = Role.getByIdentity(roleInt);
                User user = new User(id, login, password, role);
                expectedUsers.add(user);
            }
            Assert.assertEquals(actualUsers, expectedUsers);
        }
    }

    @Test(dependsOnMethods = {"testCreate"}, dataProvider = "dataUserProvider")
    public void testFindById(User expectedUser) throws ServiceException {
        int id = userService.create(expectedUser);
        expectedUser.setId(id);
        User actualUser = userService.findById(expectedUser.getId());
        Assert.assertEquals(actualUser, expectedUser);
    }

    @Test(dependsOnMethods = {"testCreate", "testFindById"}, dataProvider = "dataUserProvider")
    public void testDelete(User expectedUser) throws ServiceException {
        int id = userService.create(expectedUser);
        expectedUser.setId(id);
        boolean isDeleted = userService.delete(expectedUser.getId());
        Assert.assertTrue(isDeleted);
        User actualUser = userService.findById(expectedUser.getId());
        Assert.assertNull(actualUser);
    }

    @DataProvider(name = "dataUserUpdateProvider")
    public Object[][] updateUser() {
        User userOne = new User("test1", "1234", Role.CLIENT);
        String passOne = "newPass1234";
        User userTwo = new User("test2", "12345", Role.CLIENT);
        User userThree = new User("test3", "123456", Role.CLIENT);
        return new Object[][]{{userOne, passOne}, {userTwo, passOne}, {userThree, passOne}};
    }

    @Test(dependsOnMethods = {"testCreate"}, dataProvider = "dataUserUpdateProvider")
    public void testUpdate(User expectedUser, String newPass) throws ServiceException {
        int id = userService.create(expectedUser);
        expectedUser.setId(id);
        expectedUser.setPassword(newPass);
        userService.update(expectedUser);
        User actualUser = userService.findById(expectedUser.getId());
        Assert.assertEquals(actualUser, expectedUser);
    }

    @Test(dependsOnMethods = {"testCreate"}, dataProvider = "dataUserProvider")
    public void testFindByLogin(User expectedUser) throws ServiceException {
        int id = userService.create(expectedUser);
        expectedUser.setId(id);
        User actualUser = userService.findByLogin(expectedUser.getLogin());
        Assert.assertEquals(actualUser, expectedUser);
    }

    @DataProvider(name = "dataUserChangeRoleProvider")
    public Object[][] changeRoleUser() {
        User userOne = new User("test1", "1234", Role.CLIENT);
        Role admin = Role.ADMIN;
        User userTwo = new User("test2", "12345", Role.CLIENT);
        User userThree = new User("test3", "123456", Role.CLIENT);
        return new Object[][]{{userOne, admin}, {userTwo, admin}, {userThree, admin}};
    }

    @Test(dependsOnMethods = {"testCreate"}, dataProvider = "dataUserChangeRoleProvider")
    public void testChangeRole(User expectedUser, Role role) throws ServiceException {
        int id = userService.create(expectedUser);
        expectedUser.setId(id);
        expectedUser.setRole(role);
        userService.changeRole(role, expectedUser.getId());
        User actualUser = userService.findById(id);
        Assert.assertEquals(actualUser, expectedUser);
    }
}