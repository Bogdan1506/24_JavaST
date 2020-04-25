package by.avdeev.pizzeria.dao.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Dough;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.entity.Size;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl extends AbstractDAO<Item> {
    private static Logger logger = LogManager.getLogger();

    @Override
    public List<Item> findAll(int begin, int end) throws DAOException {
        logger.debug("begin={}, end={}", begin, end);
        List<Item> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, product_id, size_id, dough_id FROM item ORDER BY id LIMIT ?, ?")) {
            preparedStatement.setInt(1, begin);
            preparedStatement.setInt(2, end);
            ResultSet rs = preparedStatement.executeQuery();
            fill(items, rs);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return items;
    }

    private void fill(List<Item> items, ResultSet rs) throws SQLException {
        while (rs.next()) {
            int id = rs.getInt("id");
            Product product = new Product();
            product.setId(rs.getInt("product_id"));
            Size size = Size.getById(rs.getInt("size_id"));
            Dough dough = Dough.getById(rs.getInt("dough_id"));
            items.add(new Item(id, product, dough, size));
        }
    }

    @Override
    public List<Item> findAll() throws DAOException {
        List<Item> items = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT id, product_id, size_id, dough_id FROM item");
            fill(items, rs);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return items;
    }

    @Override
    public Item findById(int id) throws DAOException {
        Item item = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT product_id, size_id, dough_id FROM item WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("product_id"));
                Size size = Size.values()[rs.getInt("size_id")];
                Dough dough = Dough.getById(rs.getInt("dough_id"));
                item = new Item(id, product, dough, size);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return item;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM item WHERE id=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Item item) throws DAOException {
        delete(item.getId());
        return true;
    }

    @Override
    public void create(Item item) throws DAOException {
        if (item.getProduct().getType() == Product.Type.PIZZA) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO item (product_id, size_id, dough_id) VALUES (?,?,?)")) {
                statement.setInt(1, item.getProduct().getId());
                statement.setInt(2, item.getSize().getId());
                statement.setInt(3, item.getDough().getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } else {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO item (product_id, size_id) VALUES (?,?)")) {
                statement.setInt(1, item.getProduct().getId());
                statement.setInt(2, item.getSize().getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void update(Item item) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE item SET size_id=?, dough_id=? WHERE id=?")) {
            statement.setInt(1, item.getSize().getId());
            statement.setInt(2, item.getDough().getId());
            statement.setInt(3, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
