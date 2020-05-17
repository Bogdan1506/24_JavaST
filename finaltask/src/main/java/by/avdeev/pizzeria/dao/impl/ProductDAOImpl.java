package by.avdeev.pizzeria.dao.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAOImpl extends AbstractDAO<Product> {
    private static Logger logger = LogManager.getLogger();

    @Override
    public List<Product> findAll(int begin, int end) throws DAOException {
        return null;
    }

    @Override
    public List<Product> findAll() throws DAOException {
        List<Product> products = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT id, type, name, description, price, picture FROM product");
            while (rs.next()) {
                int id = rs.getInt("id");
                Product.Type type = Product.Type.valueOf(rs.getString("type").toUpperCase());
                logger.debug("type={}", type);
                fill(products, rs, id, type);
            }
        } catch (SQLException | IOException e) {
            rollback();
            throw new DAOException(e);
        }
        return products;
    }

    private void fill(List<Product> products, ResultSet rs, int id, Product.Type type) throws SQLException, IOException {
        String name = rs.getString("name");
        String description = rs.getString("description");
        double price = rs.getDouble("price");
        String picture = receivePicture(rs);

        products.add(new Product(id, type, name, description, price, picture));
    }

    private String receivePicture(ResultSet rs) throws SQLException, IOException {
        Blob blob = rs.getBlob("picture");
        InputStream inputStream = blob.getBinaryStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        byte[] imageBytes = outputStream.toByteArray();
        inputStream.close();
        outputStream.close();
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    @Override
    public Product findById(int id) throws DAOException {
        Product product = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT name, type, description, price, picture FROM product WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                Product.Type type = Product.Type.valueOf(rs.getString("type").toUpperCase());
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String picture = receivePicture(rs);
                product = new Product(id, type, name, description, price, picture);
            }
        } catch (SQLException | IOException e) {
            rollback();
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
            rollback();
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
            rollback();
            throw new DAOException(e);
        }
    }

    public void create(Product product, InputStream picture) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO product (id, type, name, description, price, picture) VALUES (?,?,?,?,?,?)")) {
            statement.setInt(1, product.getId());
            statement.setString(2, String.valueOf(product.getType()).toLowerCase());
            statement.setString(3, product.getName());
            statement.setString(4, product.getDescription());
            statement.setDouble(5, product.getPrice());
            statement.setBlob(6, picture);
            statement.executeUpdate();
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
    }

    public void update(Product product, InputStream picture) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE product SET name=?, type=?, description=?, price=?, picture=? WHERE id=?")) {
            statement.setString(1, product.getName());
            statement.setString(2, String.valueOf(product.getType()).toLowerCase());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getPrice());
            statement.setBlob(5, picture);
            statement.setInt(6, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Product product) throws DAOException {
        logger.debug("Connection={}", connection);
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE product SET name=?, type=?, description=?, price=? WHERE id=?")) {
            statement.setString(1, product.getName());
            statement.setString(2, String.valueOf(product.getType()).toLowerCase());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
    }

    @Override
    public int countAll() {
        return 0;
    }

    public List<Product> findByType(Product.Type type) throws DAOException {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, name, description, price, picture FROM product WHERE type=?")) {
            statement.setString(1, String.valueOf(type));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                fill(products, rs, id, type);
            }
        } catch (SQLException | IOException e) {
            rollback();
            throw new DAOException(e);
        }
        return products;
    }

    public Product findByName(String name) throws DAOException {
        Product product = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, type, description, price, picture FROM product WHERE name=?")) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                product = findById(id);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return product;
    }

    public Map<String, Integer> findCountProduct() throws DAOException {
        Map<String, Integer> res = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT name, count_item FROM (SELECT `item_id`, COUNT(`item_id`) as `count_item`, `product_id` FROM `item` JOIN `order_position` ON order_position.item_id = item.id group by `item_id`) " +
                    "as ds JOIN product ON product_id = product.id group by `name`");
            while (rs.next()) {
                String name = rs.getString("name");
                int count = rs.getInt("count_item");
                res.put(name, count);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return res;
    }
}
