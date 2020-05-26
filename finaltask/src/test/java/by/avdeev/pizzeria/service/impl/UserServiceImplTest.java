package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.transaction.Transaction;
import by.avdeev.pizzeria.transaction.TransactionImpl;
import by.avdeev.pizzeria.transaction.Type;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static by.avdeev.pizzeria.command.ConstantRepository.LOGIN;
import static by.avdeev.pizzeria.command.ConstantRepository.NEW_PASS;
import static by.avdeev.pizzeria.command.ConstantRepository.OLD_PASS;
import static by.avdeev.pizzeria.command.ConstantRepository.PASS;
import static by.avdeev.pizzeria.command.ConstantRepository.REP_PASS;
import static org.testng.Assert.*;

public class UserServiceImplTest {
    private Connection connection;
    private UserService userService;

    @BeforeTest
    public void setUpConnection() {
        try {
            Properties properties = new Properties();
            FileInputStream inputStream = new FileInputStream("src/test/resources/connection.properties");
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
        userService.setDAOType(Type.USER);
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

    @DataProvider(name = "positiveDataUserProviderForCreate")
    public Object[][] createPositiveUserData() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(LOGIN, "user1");
        parameters.put(PASS, "pass1234");
        parameters.put(REP_PASS, "pass1234");
        Map<String, Object> parameters2 = new HashMap<>();
        parameters2.put(LOGIN, "user2");
        parameters2.put(PASS, "pass12345");
        parameters2.put(REP_PASS, "pass12345");
        Map<String, String> invalidParameters = new HashMap<>();
        Map<String, String> invalidParameters2 = new HashMap<>();
        return new Object[][]{{parameters, invalidParameters}, {parameters2, invalidParameters2}};
    }

    @Test(dataProvider = "positiveDataUserProviderForCreate")
    public void testCreatePositiveData(Map<String, Object> parameters, Map<String, String> invalidParameters) throws ServiceException {
        int actualId = userService.create(parameters, invalidParameters);
        assertNotEquals(-1, actualId);
        assertTrue(invalidParameters.isEmpty());
    }

    @DataProvider(name = "negativeDataUserProviderForCreate")
    public Object[][] createNegativeUserData() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(LOGIN, "admin");
        parameters.put(PASS, "pass1234");
        parameters.put(REP_PASS, "pass1234");
        Map<String, Object> parameters2 = new HashMap<>();
        parameters2.put(LOGIN, "user2");
        parameters2.put(PASS, "pass");
        parameters2.put(REP_PASS, "pass12345");
        Map<String, String> invalidParameters = new HashMap<>();
        Map<String, String> invalidParameters2 = new HashMap<>();
        return new Object[][]{{parameters, invalidParameters}, {parameters2, invalidParameters2}};
    }

    @Test(dataProvider = "negativeDataUserProviderForCreate")
    public void testCreateNegativeData(Map<String, Object> parameters, Map<String, String> invalidParameters) throws ServiceException {
        int actualId = userService.create(parameters, invalidParameters);
        assertEquals(-1, actualId);
        assertFalse(invalidParameters.isEmpty());
    }

    @DataProvider(name = "findAllDataPositiveProvide")
    public Object[][] createPositiveDataForFindByAll() {
        Profile profileAdmin = new Profile();
        profileAdmin.setId(1);
        Profile profileCreator = new Profile();
        profileCreator.setId(2);
        Profile profileClient = new Profile();
        profileClient.setId(3);
        List<User> expectedUsers = new ArrayList<>(Arrays.asList(new User(1, "admin", "$2a$10$mP5sKmL7IHHq8Q4whdRxku72ZnjkmB7UIkL1S6yH5.DJERMmXUQAm", profileAdmin, Role.ADMIN),
                new User(2, "creator", "$2a$10$rBvV1aYev0xJfJlCr6nAAedJ1gvdoUOiMfs0B047JSXjk7cX8xNOm", profileCreator, Role.CREATOR), new User(3, "client", "$2a$10$JS5VSr5D4/ij7N4DZR.PDOVAUYM14Lwc6ZDXOlUmgcg7uwPic7/ze", profileClient, Role.CLIENT)));
        return new Object[][]{{expectedUsers, 0, 4}};
    }

    @Test(dataProvider = "findAllDataPositiveProvide")
    public void testPositiveFindAll(List<User> expectedUsers, int begin, int end) throws ServiceException {
        List<User> actualUsers = userService.findAll(begin, end);
        assertEquals(expectedUsers, actualUsers);
    }

    @DataProvider(name = "findAllDataNegativeProvide")
    public Object[][] createNegativeDataForFindByAll() {
        return new Object[][]{{-5, 10}};
    }

    @Test(dataProvider = "findAllDataNegativeProvide", expectedExceptions = ServiceException.class)
    public void testNegativeFindAll(int begin, int end) throws ServiceException {
        userService.findAll(begin, end);
    }

    @DataProvider(name = "dataUserProviderForFindById")
    public Object[][] createDataForFindById() {
        Profile profile1 = new Profile();
        profile1.setId(1);
        Profile profile2 = new Profile();
        profile2.setId(3);
        return new Object[][]{{1, new User(1, "admin", "$2a$10$mP5sKmL7IHHq8Q4whdRxku72ZnjkmB7UIkL1S6yH5.DJERMmXUQAm", profile1, Role.ADMIN)},
                {3, new User(3, "client", "$2a$10$JS5VSr5D4/ij7N4DZR.PDOVAUYM14Lwc6ZDXOlUmgcg7uwPic7/ze", profile2, Role.CLIENT)}};
    }

    @Test(dataProvider = "dataUserProviderForFindById")
    public void testFindById(int id, User expectedUser) throws ServiceException {
        User actualUser = userService.findById(id);
        assertEquals(actualUser, expectedUser);
    }

    @DataProvider(name = "dataUserProviderForDelete")
    public Object[][] createDataForDelete() {
        return new Object[][]{{true, 1}, {true, 2}};
    }

    @Test(dataProvider = "dataUserProviderForDelete")
    public void testDelete(boolean expected, int id) throws ServiceException {
        boolean actual = userService.delete(id);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "positiveDataUserProviderForChangePassword")
    public Object[][] createPositiveDataForChangePassword() {
        Map<String, Object> parameters1 = new HashMap<>();
        parameters1.put(OLD_PASS, "client");
        parameters1.put(NEW_PASS, "client1234567");
        Map<String, Object> parameters2 = new HashMap<>();
        parameters2.put(OLD_PASS, "admin");
        parameters2.put(NEW_PASS, "admin1234567");
        Map<String, String> invalidParameters = new HashMap<>();
        return new Object[][]{{parameters1, invalidParameters, "client"}, {parameters2, invalidParameters, "admin"}};
    }

    @Test(dataProvider = "positiveDataUserProviderForChangePassword")
    public void testChangePasswordPositive(Map<String, Object> parameters, Map<String, String> invalidParameters, String login) throws ServiceException {
        assertTrue(userService.changePassword(parameters, invalidParameters, login));
        assertTrue(invalidParameters.isEmpty());
    }

    @DataProvider(name = "negativeDataUserProviderForChangePassword")
    public Object[][] createNegativeDataForChangePassword() {
        Map<String, Object> parameters1 = new HashMap<>();
        parameters1.put(OLD_PASS, "$2a$10$JS5VSr5D4/ij7N4DZR.PDOVAUYM14Lwc6ZDXOlUmgcg7uwPic7");
        parameters1.put(NEW_PASS, "client1234567");
        Map<String, Object> parameters2 = new HashMap<>();
        parameters2.put(OLD_PASS, "$2a$10$mP5sKmL7IHHq8Q4whdRxku72ZnjkmB7UIkL1S6yH5.DJERMmXUQAm");
        parameters2.put(NEW_PASS, "admin12!34567");
        Map<String, String> invalidParameters = new HashMap<>();
        return new Object[][]{{parameters1, invalidParameters, "client"}, {parameters2, invalidParameters, "admin"}};
    }

    @Test(dataProvider = "negativeDataUserProviderForChangePassword")
    public void testChangePasswordNegative(Map<String, Object> parameters, Map<String, String> invalidParameters, String login) throws ServiceException {
        assertFalse(userService.changePassword(parameters, invalidParameters, login));
    }

    @DataProvider(name = "findByLogin")
    public Object[][] createDataForFindByLogin() {
        Profile profile1 = new Profile();
        profile1.setId(1);
        Profile profile2 = new Profile();
        profile2.setId(3);
        return new Object[][]{
                {new User(1, "admin", "$2a$10$mP5sKmL7IHHq8Q4whdRxku72ZnjkmB7UIkL1S6yH5.DJERMmXUQAm", profile1, Role.ADMIN), "admin"},
                {new User(3, "client", "$2a$10$JS5VSr5D4/ij7N4DZR.PDOVAUYM14Lwc6ZDXOlUmgcg7uwPic7/ze", profile2, Role.CLIENT), "client"},
                {null, null}
        };
    }

    @Test(dataProvider = "findByLogin")
    public void testFindByLoginPositive(User expectedUser, String login) throws ServiceException {
        User actualUser = userService.findByLogin(login);
        assertEquals(actualUser, expectedUser);
    }

    @Test
    public void testChangeRole() {
    }

    @DataProvider(name = "positiveUserLoginProvider")
    public Object[][] createPositiveDataForUserLogin() {
        Profile profile1 = new Profile();
        profile1.setId(1);
        Profile profile2 = new Profile();
        profile2.setId(3);
        return new Object[][]{
                {true, new User(1, "admin", "$2a$10$mP5sKmL7IHHq8Q4whdRxku72ZnjkmB7UIkL1S6yH5.DJERMmXUQAm", profile1, Role.ADMIN), "admin"},
                {true, new User(3, "client", "$2a$10$JS5VSr5D4/ij7N4DZR.PDOVAUYM14Lwc6ZDXOlUmgcg7uwPic7/ze", profile2, Role.CLIENT), "client"},
                {false, new User(3, "client", "$2a$10$JS5VSr5DDZR.PDOVAUYM14Lwc6ZDXOlUmgcg7uwPic7/ze", profile2, Role.CLIENT), "client"},
                {false, null, null}
        };
    }

    @Test(dataProvider = "positiveUserLoginProvider")
    public void testPositiveUserLogin(boolean expected, User user, String password) throws ServiceException {
        boolean actual = userService.userLogin(user, password);
        assertEquals(actual, expected);
    }
}