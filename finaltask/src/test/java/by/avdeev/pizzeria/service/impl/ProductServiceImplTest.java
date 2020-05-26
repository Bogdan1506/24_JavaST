package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static by.avdeev.pizzeria.command.ConstantRepository.DESCRIPTION;
import static by.avdeev.pizzeria.command.ConstantRepository.NAME;
import static by.avdeev.pizzeria.command.ConstantRepository.PICTURE;
import static by.avdeev.pizzeria.command.ConstantRepository.PRICE;
import static by.avdeev.pizzeria.command.ConstantRepository.TYPE;
import static org.testng.Assert.*;

public class ProductServiceImplTest {
    private Connection connection;
    private ProductService productService;

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
        productService = new ProductServiceImpl();
        Transaction transaction = new TransactionImpl(connection);
        productService.setTransaction(transaction);
        productService.setDAOType(Type.PRODUCT);
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

    @DataProvider(name = "dataProviderForFindById")
    public Object[][] createDataForFindById() {
        return new Object[][]{
                {1, new Product(1, Product.Type.PIZZA, "Margherita", "Tomato pizza sauce, mozzarella, tomatoes, oregano", 6.9, "margherita.jpg")},
                {-1, null}
        };
    }

    @Test(dataProvider = "dataProviderForFindById")
    public void testFindById(int id, Product expectedProduct) throws ServiceException {
        Product actualProduct = productService.findById(id);
        assertEquals(actualProduct, expectedProduct);
    }

    @DataProvider(name = "dataProviderForFindByType")
    public Object[][] createDataForFindByType() {
        List<Product> products = Arrays.asList(new Product(11, Product.Type.DRINK, "Coca-Cola", "Sugar, water, vanilla", 2, "coca-cola.png"),
                new Product(12, Product.Type.DRINK, "Fanta", "Sugar, water, orange", 2, "fanta.png"),
                new Product(13, Product.Type.DRINK, "Sprite", "Sugar, water, lime", 2, "sprite.png"));
        return new Object[][]{
                {Product.Type.DRINK, products}};
    }

    @Test(dataProvider = "dataProviderForFindByType")
    public void testFindByType(Product.Type type, List<Product> expectedProducts) throws ServiceException {
        List<Product> actualProducts = productService.findByType(type);
        assertEquals(actualProducts, expectedProducts);
    }

    @DataProvider(name = "dataProviderForCreate")
    public Object[][] createDataForCreate() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", Product.Type.PIZZA);
        parameters.put("name", "Cheese");
        parameters.put("description", "Tomato pizza sauce, mozzarella, tomatoes, oregano");
        parameters.put("price", 7.0);
        parameters.put("picture", "cheese.jpg");
        Map<String, String> invalidParameters = new HashMap<>();
        return new Object[][]{
                {parameters, invalidParameters}};
    }

    @Test(dataProvider = "dataProviderForCreate")
    public void testCreate(Map<String, Object> parameters, Map<String, String> invalidParameters) throws ServiceException {
        int id = productService.create(parameters, invalidParameters);
        assertTrue(id != -1);
        assertTrue(invalidParameters.isEmpty());
    }

    @DataProvider(name = "dataProviderForFindByName")
    public Object[][] createDataForFindByName() {
        return new Object[][]{
                {"Coca-Cola", new Product(11, Product.Type.DRINK, "Coca-Cola", "Sugar, water, vanilla", 2, "coca-cola.png")},
                {"Fanta Orange", new Product(12, Product.Type.DRINK, "Fanta", "Sugar, water, orange", 2, "fanta.png")},
                {"Sprite", new Product(13, Product.Type.DRINK, "Sprite", "Sugar, water, lime", 2, "sprite.png")},
                {null, null}
        };
    }

    @DataProvider(name = "dataProviderForUpdate")
    public Object[][] createDataForUpdate() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(TYPE, Product.Type.PIZZA);
        parameters.put(NAME, "Cheese");
        parameters.put(DESCRIPTION, "Tomato pizza sauce, mozzarella, tomatoes, oregano");
        parameters.put(PRICE, 7.0);
        parameters.put(PICTURE, "cheese.jpg");
        Map<String, String> invalidParameters = new HashMap<>();
        return new Object[][]{
                {parameters, invalidParameters, 1}};
    }

    @Test(dataProvider = "dataProviderForUpdate")
    public void testUpdate(Map<String, Object> parameters, Map<String, String> invalidParameters, int id) throws ServiceException {
        int actualId = productService.update(parameters, invalidParameters, id);
        assertTrue(actualId != -1);
        assertTrue(invalidParameters.isEmpty());
    }
}