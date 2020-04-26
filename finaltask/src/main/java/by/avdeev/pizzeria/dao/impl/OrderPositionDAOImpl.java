package by.avdeev.pizzeria.dao.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.Order;
import by.avdeev.pizzeria.entity.OrderPosition;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderPositionDAOImpl extends AbstractDAO<OrderPosition> {
    @Override
    public List<OrderPosition> findAll(int begin, int end) throws DAOException {
        return null;
    }

    @Override
    public List<OrderPosition> findAll() throws DAOException {
        List<OrderPosition> orderPositions = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT id, item_id, order_id, price FROM `order_position`");
            while (rs.next()) {
                orderPositions.add(fill(rs));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orderPositions;
    }

    private OrderPosition fill(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        Item item = new Item();
        int itemId = rs.getInt("item_id");
        item.setId(itemId);
        Order order = new Order();
        int orderId = rs.getInt("order_id");
        order.setId(orderId);
        double price = rs.getDouble("price");
        return new OrderPosition(id, item, order, price);
    }

    @Override
    public OrderPosition findById(int id) throws DAOException {
        OrderPosition orderPosition = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT item_id, order_id, price FROM `order_position` WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                orderPosition = fill(rs);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orderPosition;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM `order_position` WHERE id=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(OrderPosition orderPosition) throws DAOException {
        delete(orderPosition.getId());
        return true;
    }

    @Override
    public void create(OrderPosition orderPosition) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO `order_position` (item_id, order_id, price) VALUES (?,?,?)")) {
            statement.setInt(1, orderPosition.getItem().getId());
            statement.setInt(2, orderPosition.getOrder().getId());
            statement.setDouble(3, orderPosition.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(OrderPosition orderPosition) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE `order_position` SET item_id=?, order_id=?, price=? WHERE id=?")) {
            statement.setInt(1, orderPosition.getItem().getId());
            statement.setInt(2, orderPosition.getOrder().getId());
            statement.setDouble(3, orderPosition.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public OrderPosition findByItem(Item item) throws DAOException {
        OrderPosition orderPosition = new OrderPosition();
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, order_id, price FROM `order_position` WHERE item_id=?")) {
            statement.setInt(1, item.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                Order order = new Order();
                int orderId = rs.getInt("order_id");
                order.setId(orderId);
                double price = rs.getDouble("price");
                orderPosition = new OrderPosition(id, item, order, price);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orderPosition;
    }

    public List<OrderPosition> findByOrderPosition(Order order) throws DAOException {
        List<OrderPosition> orderPositions = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, item_id, price FROM `order_position` WHERE order_id=?")) {
            statement.setInt(1, order.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                Item item = new Item();
                int itemId = rs.getInt("item_id");
                item.setId(itemId);
                double price = rs.getDouble("price");
                orderPositions.add(new OrderPosition(id, item, order, price));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orderPositions;
    }
}
