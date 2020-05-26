package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.entity.Dough;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.entity.Size;
import by.avdeev.pizzeria.service.ItemService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static by.avdeev.pizzeria.command.ConstantRepository.DOUGH;
import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.SIZE;
import static org.testng.Assert.*;

public class ItemServiceImplTest {
    private Connection connection;
    private ItemService itemService;

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
        itemService = new ItemServiceImpl();
        Transaction transaction = new TransactionImpl(connection);
        itemService.setTransaction(transaction);
        itemService.setDAOType(Type.ITEM);
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


    @DataProvider(name = "dataForFindById")
    public Object[] createDataForFindById() {
        return new Object[][]{
                {1, new Item(new Product(1), Dough.THIN, Size.SMALL)},
                {2, new Item(new Product(2), Dough.THICK, Size.SMALL)},
                {0, null}
        };
    }

    @Test(dataProvider = "dataForFindById")
    public void testForFindById(int id, Item expectedItem) throws ServiceException {
        Item actualItem = itemService.findById(id);
        assertEquals(actualItem, expectedItem);
    }

    @DataProvider(name = "dataForCreate")
    public Object[] createDataForCreate() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(ID, 1);
        parameters.put(SIZE, Size.SMALL);
        parameters.put(DOUGH, Dough.THICK);
        return new Object[][]{
                {parameters, false},
                {null, true}
        };
    }

    @Test(dataProvider = "dataForCreate")
    public void testCreate(Map<String, Object> parameters, boolean expected) throws ServiceException {
        List<Item> cart = new ArrayList<>();
        itemService.create(parameters, cart);
        assertEquals(cart.isEmpty(), expected);
    }
}