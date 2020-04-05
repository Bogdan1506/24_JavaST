package by.avdeev.pizzeria.dao.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
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
            ResultSet rs = statement.executeQuery("SELECT id, name, description, price, picture FROM product");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String picture = rs.getString("picture");
                products.add(new Product(id, name, description, price, picture));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return products;
    }

    @Override
    public Product findById(int id) throws DAOException {
        Product product = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT name, description, price, picture FROM product WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String picture = rs.getString("picture");
                product = new Product(id, name, description, price, picture);
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
                "INSERT INTO product (id, name, description, price, picture) VALUES (?,?,?,?,?)")) {
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getPrice());
            statement.setString(5, product.getPicture());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Product product) throws DAOException {
        int id = product.getId();
        String name = product.getName();
        String description = product.getDescription();
        double price = product.getPrice();
        String picture = product.getPicture();
        logger.debug(String.format("Connection=%s", connection));
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE product SET name=?, description=?, price=?, picture=? WHERE id=?")) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getPicture());
            statement.setInt(5, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
