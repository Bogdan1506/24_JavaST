package by.avdeev.pizzeria.dao.impl;

import  by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl extends AbstractDAO<Product> {
    private static Logger logger = LogManager.getLogger();

    @Override
    public List<Product> findAll() throws DAOException {
        List<Product> products = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT id, type, name, description, price FROM product");
            while (rs.next()) {
                int id = rs.getInt("id");
                Product.Type type = Product.Type.valueOf(rs.getString("type").toUpperCase());
                logger.debug("type={}", type);
                fill(products, rs, id, type);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return products;
    }

    private void fill(List<Product> products, ResultSet rs, int id, Product.Type type) throws SQLException {
        String name = rs.getString("name");
        String description = rs.getString("description");
        double price = rs.getDouble("price");
        products.add(new Product(id, type, name, description, price));
    }

    @Override
    public Product findById(int id) throws DAOException {
        Product product = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT name, type, description, price FROM product WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                Product.Type type = Product.Type.valueOf(rs.getString("type").toUpperCase());
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                product = new Product(id, type, name, description, price);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return product;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM product WHERE id=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Product product) throws DAOException {
        delete(product.getId());
        return true;
    }

    @Override
    public void create(Product product) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO product (id, type, name, description, price) VALUES (?,?,?,?,?)")) {
            statement.setInt(1, product.getId());
            statement.setString(2, String.valueOf(product.getType()).toLowerCase());
            statement.setString(3, product.getName());
            statement.setString(4, product.getDescription());
            statement.setDouble(5, product.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Product product) throws DAOException {
        logger.debug(String.format("Connection=%s", connection));
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE product SET name=?, type=?, description=?, price=? WHERE id=?")) {
            statement.setString(1, product.getName());
            statement.setString(2, String.valueOf(product.getType()).toLowerCase());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public List<Product> findByType(Product.Type type) throws DAOException {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, name, description, price FROM product WHERE type=?")) {
            statement.setString(1, String.valueOf(type));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                fill(products, rs, id, type);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return products;
    }
}
