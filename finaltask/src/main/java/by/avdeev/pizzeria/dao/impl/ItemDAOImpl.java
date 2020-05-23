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

import static by.avdeev.pizzeria.command.ConstantRepository.COUNT;
import static by.avdeev.pizzeria.command.ConstantRepository.DOUGH_ID;
import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.PRODUCT_ID;
import static by.avdeev.pizzeria.command.ConstantRepository.SIZE_ID;

public class ItemDAOImpl extends AbstractDAO<Item> {
    private static Logger logger = LogManager.getLogger(ItemDAOImpl.class);

    @Override
    public List<Item> findAll(int begin, int end) throws DAOException {
        logger.debug("begin={}, end={}", begin, end);
        List<Item> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, product_id, size_id, dough_id FROM item ORDER BY id LIMIT ?, ?")) {
            preparedStatement.setInt(1, begin);
            preparedStatement.setInt(2, end);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                fill(rs);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return items;
    }

    @Override
    public List<Item> findAll() throws DAOException {
        List<Item> items = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT id, product_id, size_id, dough_id FROM item");
            while (rs.next()) {
                items.add(fill(rs));
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return items;
    }

    @Override
    public Item findById(int id) throws DAOException {
        Item item = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, product_id, size_id, dough_id FROM item WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                item = fill(rs);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return item;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM item WHERE id=?")) {
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Item item) throws DAOException {
        delete(item.getId());
        return true;
    }

    @Override
    public int create(Item item) throws DAOException {
        int id;
        if (item.getProduct().getType() == Product.Type.PIZZA) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO item (product_id, size_id, dough_id) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, item.getProduct().getId());
                statement.setInt(2, item.getSize().getId());
                statement.setInt(3, item.getDough().getId());
                statement.executeUpdate();
                id = findLastId(statement);
            } catch (SQLException e) {
                rollback();
                throw new DAOException(e);
            }
        } else {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO item (product_id, size_id) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, item.getProduct().getId());
                statement.setInt(2, item.getSize().getId());
                statement.executeUpdate();
                id = findLastId(statement);
            } catch (SQLException e) {
                rollback();
                throw new DAOException(e);
            }
        }
        return id;
    }

    @Override
    public boolean update(Item item) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE item SET size_id=?, dough_id=? WHERE id=?")) {
            preparedStatement.setInt(1, item.getSize().getId());
            preparedStatement.setInt(2, item.getDough().getId());
            preparedStatement.setInt(3, item.getId());
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
    }

    @Override
    public int countAll() throws DAOException {
        int total = 0;
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT COUNT(*) as count FROM `item`";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                total = rs.getInt(COUNT);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return total;
    }

    private Item fill(ResultSet rs) throws SQLException {
        Size size = Size.getById(rs.getInt(SIZE_ID));
        Dough dough = Dough.getById(rs.getInt(DOUGH_ID));
        return new Item(rs.getInt(ID),
                new Product(rs.getInt(PRODUCT_ID)),
                dough, size);
    }
}
