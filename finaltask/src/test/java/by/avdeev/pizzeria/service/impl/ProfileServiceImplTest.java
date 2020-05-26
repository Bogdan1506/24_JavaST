package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.service.ProfileService;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static by.avdeev.pizzeria.command.ConstantRepository.ADDRESS;
import static by.avdeev.pizzeria.command.ConstantRepository.EMAIL;
import static by.avdeev.pizzeria.command.ConstantRepository.NAME;
import static by.avdeev.pizzeria.command.ConstantRepository.PHONE;
import static by.avdeev.pizzeria.command.ConstantRepository.SURNAME;
import static org.testng.Assert.*;

public class ProfileServiceImplTest {
    private Connection connection;
    private ProfileService profileService;

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
        profileService = new ProfileServiceImpl();
        Transaction transaction = new TransactionImpl(connection);
        profileService.setTransaction(transaction);
        profileService.setDAOType(Type.PROFILE);
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


    @DataProvider(name = "dataPositiveProviderForFindById")
    public Object[][] createDataForFindById() {
        return new Object[][]{
                {1, new Profile(1, "admin", "admin", "admin@mail.ru", "375291234567", "admin")},
                {2, new Profile(2, "creator", "creator", "creator@mail.ru", "375291234567", "creator")},
                {-1, null}
        };
    }

    @Test(dataProvider = "dataPositiveProviderForFindById")
    public void testPositiveFindById(int id, Profile expectedProfile) throws ServiceException {
        Profile actualProfile = profileService.findById(id);
        assertEquals(actualProfile, expectedProfile);
    }

    @DataProvider(name = "dataProviderForCreate")
    public Object[][] createDataForCreate() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(NAME, "Bogdan");
        parameters.put(SURNAME, "Avdeev");
        parameters.put(PHONE, "37529");
        parameters.put(EMAIL, "email@mail.ru");
        parameters.put(ADDRESS, "Minsk");
        Map<String, String> invalidParameters = new HashMap<>();
        return new Object[][]{
                {parameters, invalidParameters}};
    }

    @Test(dataProvider = "dataProviderForCreate")
    public void testCreate(Map<String, Object> parameters, Map<String, String> invalidParameters) throws ServiceException {
        int id = profileService.create(parameters, invalidParameters);
        assertTrue(id != -1);
        assertTrue(invalidParameters.isEmpty());
    }

    @DataProvider(name = "dataProviderForUpdate")
    public Object[][] createDataForUpdate() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(NAME, "Bogdan");
        parameters.put(SURNAME, "Avdeev");
        parameters.put(PHONE, "37529");
        parameters.put(EMAIL, "email@mail.ru");
        parameters.put(ADDRESS, "Minsk");
        Map<String, String> invalidParameters = new HashMap<>();
        return new Object[][]{
                {parameters, invalidParameters, 1}};
    }

    @Test(dataProvider = "dataProviderForUpdate")
    public void testUpdate(Map<String, Object> parameters, Map<String, String> invalidParameters, int id) throws ServiceException {
        boolean isUpdated = profileService.update(parameters, invalidParameters, id);
        assertTrue(isUpdated);
    }

    @DataProvider(name = "dataProviderForFindByLogin")
    public Object[][] testFindByLogin() {
        return new Object[][]{
                {"client", new Profile(3, "client", "client", "client@mail.ru", "375291234567", "client")}
        };
    }

    @Test(dataProvider = "dataProviderForFindByLogin")
    public void testFindByUserLogin(String login, Profile expectedProfile) throws ServiceException {
        Profile actualProfile = profileService.findByUserLogin(login);
        assertEquals(actualProfile, expectedProfile);
    }
}