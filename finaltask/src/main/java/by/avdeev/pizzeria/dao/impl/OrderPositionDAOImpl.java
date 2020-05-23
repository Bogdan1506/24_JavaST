package by.avdeev.pizzeria.dao.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.Order;
import by.avdeev.pizzeria.entity.OrderPosition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.avdeev.pizzeria.command.ConstantRepository.COUNT;
import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.ITEM_ID;
import static by.avdeev.pizzeria.command.ConstantRepository.ORDER_ID;
import static by.avdeev.pizzeria.command.ConstantRepository.PRICE;

public class OrderPositionDAOImpl extends AbstractDAO<OrderPosition> {
    private final static Logger logger = LogManager.getLogger(OrderPositionDAOImpl.class);

    @Override
    public List<OrderPosition> findAll(int begin, int end) throws DAOException {
        List<OrderPosition> orderPositions = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, item_id, order_id, price FROM `order_position`" +
                        "ORDER BY id LIMIT ?, ?")) {
            preparedStatement.setInt(1, begin);
            preparedStatement.setInt(2, end);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                orderPositions.add(fill(rs));
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return orderPositions;
    }

    @Override
    public List<OrderPosition> findAll() throws DAOException {
        List<OrderPosition> orderPositions = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(
                    "SELECT id, item_id, order_id, price FROM `order_position`");
            while (rs.next()) {
                orderPositions.add(fill(rs));
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return orderPositions;
    }


    @Override
    public OrderPosition findById(int id) throws DAOException {
        OrderPosition orderPosition = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, item_id, order_id, price FROM `order_position` WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                orderPosition = fill(rs);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return orderPosition;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM `order_position` WHERE id=?")) {
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(OrderPosition orderPosition) throws DAOException {
        delete(orderPosition.getId());
        return true;
    }

    @Override
    public int create(OrderPosition orderPosition) throws DAOException {
        int id;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO `order_position` (item_id, order_id, price) VALUES (?,?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
            fill(preparedStatement, orderPosition);
            preparedStatement.executeUpdate();
            id = findLastId(preparedStatement);
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return id;
    }

    @Override
    public boolean update(OrderPosition orderPosition) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE `order_position` SET item_id=?, order_id=?, price=? WHERE id=?")) {
            fill(preparedStatement, orderPosition);
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
            String sql = "SELECT COUNT(*) AS count FROM order_position";
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

    public OrderPosition findByItem(Item item) throws DAOException { //todo delete
        OrderPosition orderPosition = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, order_id, price FROM `order_position` WHERE item_id=?")) {
            statement.setInt(1, item.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                orderPosition = fill(rs);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return orderPosition;
    }

    public List<OrderPosition> findByOrderPosition(Order order) throws DAOException { //todo delete
        logger.debug("order={}", order);
        List<OrderPosition> orderPositions = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, item_id, price FROM `order_position` WHERE order_id=?")) {
            preparedStatement.setInt(1, order.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                orderPositions.add(fill(rs));
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        logger.debug("orderPos={}", orderPositions);
        return orderPositions;
    }

    private void fill(PreparedStatement preparedStatement,
                      OrderPosition orderPosition) throws SQLException {
        preparedStatement.setInt(1, orderPosition.getItem().getId());
        preparedStatement.setInt(2, orderPosition.getOrder().getId());
        preparedStatement.setDouble(3, orderPosition.getPrice());
    }


    private OrderPosition fill(ResultSet rs) throws SQLException {
        return new OrderPosition(rs.getInt(ID),
                new Item(rs.getInt(ITEM_ID)),
                new Order(rs.getInt(ORDER_ID)),
                rs.getDouble(PRICE));
    }
}
