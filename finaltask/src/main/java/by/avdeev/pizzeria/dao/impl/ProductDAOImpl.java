package by.avdeev.pizzeria.dao.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.COUNT_ITEM;
import static by.avdeev.pizzeria.command.ConstantRepository.DESCRIPTION;
import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.NAME;
import static by.avdeev.pizzeria.command.ConstantRepository.PICTURE;
import static by.avdeev.pizzeria.command.ConstantRepository.PRICE;
import static by.avdeev.pizzeria.command.ConstantRepository.TYPE;

public class ProductDAOImpl extends AbstractDAO<Product> {
    @Override
    public List<Product> findAll(int begin, int end) throws DAOException {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, type, name, description, price, picture FROM product LIMIT ?, ?")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                products.add(fill(rs));
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return products;
    }

    @Override
    public List<Product> findAll() throws DAOException {
        List<Product> products = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(
                    "SELECT id, type, name, description, price, picture FROM product");
            while (rs.next()) {
                products.add(fill(rs));
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return products;
    }


    @Override
    public Product findById(int id) throws DAOException {
        Product product = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, name, type, description, price, picture FROM product WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                product = fill(rs);
            }
        } catch (SQLException e) {
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
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
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
    public int create(Product product) throws DAOException {
        int id;
        if (product.getPicture() == null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO product ( type, name, description, price) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                fill(preparedStatement, product);
                preparedStatement.executeUpdate();
                id = findLastId(preparedStatement);
            } catch (SQLException e) {
                rollback();
                throw new DAOException(e);
            }
        } else {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO product ( type, name, description, price, picture) VALUES (?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                fill(preparedStatement, product);
                preparedStatement.setString(5, product.getPicture());
                preparedStatement.executeUpdate();
                id = findLastId(preparedStatement);
            } catch (SQLException e) {
                rollback();
                throw new DAOException(e);
            }
        }
        return id;
    }

    public boolean update(Product product) throws DAOException {
        if (product.getPicture() == null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE product SET type=?, name=?, description=?, price=? WHERE id=?")) {
                fill(preparedStatement, product);
                preparedStatement.setInt(5, product.getId());
                int rows = preparedStatement.executeUpdate();
                return rows > 0;
            } catch (SQLException e) {
                rollback();
                throw new DAOException(e);
            }
        } else {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE product SET type=?, name=?, description=?, price=?, picture=? WHERE id=?")) {
                fill(preparedStatement, product);
                preparedStatement.setString(5, product.getPicture());
                preparedStatement.setInt(6, product.getId());
                int rows = preparedStatement.executeUpdate();
                return rows > 0;
            } catch (SQLException e) {
                rollback();
                throw new DAOException(e);
            }
        }
    }

    @Override
    public int countAll() {
        return 0;
    }

    public List<Product> findByType(Product.Type type) throws DAOException {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, name, description, price, picture, type FROM product WHERE type=?")) {
            statement.setString(1, String.valueOf(type));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                products.add(fill(rs));
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return products;
    }

    public Product findByName(String name) throws DAOException {
        Product product = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, name, type, description, price, picture FROM product WHERE name=?")) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                product = fill(rs);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return product;
    }

    public Map<String, Integer> findCountProduct() throws DAOException {
        Map<String, Integer> countProducts = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(
                    "SELECT name, count_item FROM(SELECT `item_id`, COUNT(`item_id`) as `count_item`, `product_id`" +
                            "FROM `item` JOIN `order_position` ON order_position.item_id = item.id group by `item_id`) " +
                            "as ds JOIN product ON product_id = product.id group by `name`");
            while (rs.next()) {
                String name = rs.getString(NAME);
                int count = rs.getInt(COUNT_ITEM);
                countProducts.put(name, count);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return countProducts;
    }

    private Product fill(ResultSet rs) throws SQLException {
        int id = rs.getInt(ID);
        Product.Type type = Product.Type.valueOf(rs.getString(TYPE).toUpperCase());
        String name = rs.getString(NAME);
        String description = rs.getString(DESCRIPTION);
        double price = rs.getDouble(PRICE);
        String picture = rs.getString(PICTURE);
        return new Product(id, type, name, description, price, picture);
    }

    private void fill(PreparedStatement preparedStatement,
                      Product product) throws SQLException {
        preparedStatement.setString(1, String.valueOf(product.getType()).toLowerCase());
        preparedStatement.setString(2, product.getName());
        preparedStatement.setString(3, product.getDescription());
        preparedStatement.setDouble(4, product.getPrice());
    }
}
