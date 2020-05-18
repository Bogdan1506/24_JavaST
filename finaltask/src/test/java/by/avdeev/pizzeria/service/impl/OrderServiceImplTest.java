package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.entity.Order;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.service.OrderService;
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

public class OrderServiceImplTest {
    private Connection connection;
    private OrderService orderService;

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
        orderService = new OrderServiceImpl();
        Transaction transaction = new TransactionImpl(connection);
        orderService.setTransaction(transaction);
        orderService.setDAOType(Type.ORDER);
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
    public void testCreate() throws ServiceException {
        Order order = new Order(new Profile(1));
        int id = orderService.create(order);
        assertTrue(id != -1);
    }

    @Test
    public void testFindAll() throws ParseException, ServiceException {
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse("2020-01-01T00:00:00");
        List<Order> expectedOrders = Arrays.asList(
                new Order(1, new Profile(1), date),
                new Order(2, new Profile(2), date),
                new Order(3, new Profile(3), date)
        );
        List<Order> actualOrders = orderService.findAll();
        assertEquals(actualOrders, expectedOrders);
    }

    @Test
    public void testFindAllLimit() throws ParseException, ServiceException {
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse("2020-01-01T00:00:00");
        List<Order> expectedOrders = Arrays.asList(
                new Order(1, new Profile(1), date),
                new Order(2, new Profile(2), date)
        );
        List<Order> actualOrders = orderService.findAll(0, 2);
        assertEquals(actualOrders, expectedOrders);
    }
}