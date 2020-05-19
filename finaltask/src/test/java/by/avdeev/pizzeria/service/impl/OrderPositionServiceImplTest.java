package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.Order;
import by.avdeev.pizzeria.entity.OrderPosition;
import by.avdeev.pizzeria.service.OrderPositionService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.*;

public class OrderPositionServiceImplTest {
    private Connection connection;
    private OrderPositionService orderPositionService;

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
        orderPositionService = new OrderPositionServiceImpl();
        Transaction transaction = new TransactionImpl(connection);
        orderPositionService.setTransaction(transaction);
        orderPositionService.setDAOType(Type.ORDER_POSITION);
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
        OrderPosition orderPosition = new OrderPosition(new Item(1), new Order(1), 14.0);
        int id = orderPositionService.create(orderPosition);
        assertNotEquals(id, -1);
    }

    @Test
    public void testFindAllLimit() throws ServiceException {
        List<OrderPosition> expectedOrderPositions = Arrays.asList(
                new OrderPosition(1, new Item(1), new Order(1), 10.0),
                new OrderPosition(2, new Item(2), new Order(2), 12.0)
        );
        List<OrderPosition> actualOrderPositions = orderPositionService.findAll(0, 2);
        assertEquals(actualOrderPositions, expectedOrderPositions);
    }
}