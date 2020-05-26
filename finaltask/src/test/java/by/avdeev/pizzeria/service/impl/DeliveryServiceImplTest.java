package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.entity.OrderPosition;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static by.avdeev.pizzeria.entity.Delivery.Payment.CARD;
import static by.avdeev.pizzeria.entity.Delivery.Payment.CASH;
import static org.testng.Assert.*;

public class DeliveryServiceImplTest {
    private Connection connection;
    private DeliveryService deliveryService;

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
        deliveryService = new DeliveryServiceImpl();
        Transaction transaction = new TransactionImpl(connection);
        deliveryService.setTransaction(transaction);
        deliveryService.setDAOType(Type.DELIVERY);
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

    @Test
    public void testPositiveCreate() throws ParseException, ServiceException {
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse("2020-01-01T01:00:00");
        Delivery delivery = new Delivery(new OrderPosition(1), date, CASH);
        int id = deliveryService.create(delivery);
        assertNotEquals(id, -1);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testNegativeCreate() throws ParseException, ServiceException {
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse("2020-01-01T01:00:00");
        Delivery delivery = new Delivery(new OrderPosition(0), date, CASH);
        deliveryService.create(delivery);
    }

    @Test
    public void testFindAll() throws ParseException, ServiceException {
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse("2020-01-01T01:00:00");
        List<Delivery> expectedDeliveries = Arrays.asList(
                new Delivery(1, new OrderPosition(1), date, CASH),
                new Delivery(2, new OrderPosition(2), date, CARD)
        );
        List<Delivery> actualDeliveries = deliveryService.findAll(0, 2);
        assertEquals(actualDeliveries, expectedDeliveries);
    }

    @DataProvider
    public Object[][] createDataForFindById() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse("2020-01-01T01:00:00");
        return new Object[][]{
                {2, new Delivery(2, new OrderPosition(2), date, CARD)},
                {0, null}
        };
    }

    @Test(dataProvider = "createDataForFindById")
    public void testFindById(int id, Delivery expectedDelivery) throws ServiceException {
        Delivery actualDelivery = deliveryService.findById(id);
        assertEquals(actualDelivery, expectedDelivery);
    }

    @DataProvider
    public Object[][] createDataForDelete() {
        return new Object[][]{
                {2, true},
                {0, false}
        };
    }

    @Test(dataProvider = "createDataForDelete")
    public void testDeleteById(int id, boolean expected) throws ServiceException {
        boolean actual = deliveryService.delete(id);
        assertEquals(actual, expected);
    }

    @Test
    public void testCountAll() throws ServiceException {
        int expectedCountTotal = 3;
        int actualCountTotal = deliveryService.countAll();
        assertEquals(actualCountTotal, expectedCountTotal);
    }


    @Test
    public void testUpdate() throws ParseException, ServiceException {
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse("2020-01-01T01:00:00");
        Delivery delivery = new Delivery(1, new OrderPosition(1), date, CARD);
        boolean isUpdated = deliveryService.update(delivery);
        assertTrue(isUpdated);
    }

    @Test
    public void testFindCountByDate() throws ServiceException {
        java.sql.Date firstDate = java.sql.Date.valueOf("2020-01-01");
        java.sql.Date secDate = java.sql.Date.valueOf("2020-01-02");
        int expected = 3;
        int actual = deliveryService.findCountByDate(firstDate, secDate);
        assertEquals(actual, expected);
    }
}