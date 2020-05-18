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
    public void testCreate() throws ParseException, ServiceException {
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse("2020-01-01T01:00:00");
        Delivery delivery = new Delivery(new OrderPosition(1), date, Delivery.Payment.CASH);
        int id = deliveryService.create(delivery);
        assertNotEquals(id, -1);
    }

    @Test
    public void testFindAll() throws ParseException, ServiceException {
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse("2020-01-01T01:00:00");
        List<Delivery> expectedDeliveries = Arrays.asList(
                new Delivery(1, new OrderPosition(1), date, Delivery.Payment.CASH),
                new Delivery(2, new OrderPosition(2), date, Delivery.Payment.CARD)
        );
        List<Delivery> actualDeliveries = deliveryService.findAll(0, 2);
        assertEquals(actualDeliveries, expectedDeliveries);
    }

    @Test
    public void testDelete() throws ServiceException {
        boolean idDeleted = deliveryService.delete(1);
        assertTrue(idDeleted);
    }

    @Test
    public void testCountAll() throws ServiceException {
        int expectedCountTotal = 3;
        int actualCountTotal = deliveryService.countAll();
        assertEquals(actualCountTotal, expectedCountTotal);
    }

    @Test
    public void testFindByOrderPosition() throws ParseException, ServiceException {
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse("2020-01-01T01:00:00");
        Delivery expectedDelivery = new Delivery(1, new OrderPosition(1), date, Delivery.Payment.CASH);
        Delivery actualDelivery = deliveryService.findByOrderPosition(new OrderPosition(1));
        assertEquals(actualDelivery, expectedDelivery);
    }
}