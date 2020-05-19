package by.avdeev.pizzeria.dao.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.entity.OrderPosition;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class DeliveryDAOImpl extends AbstractDAO<Delivery> {
    @Override
    public List<Delivery> findAll() throws DAOException {
        List<Delivery> deliveries = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT id, order_position_id, date, payment FROM `delivery`");
            fill(deliveries, rs);
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return deliveries;
    }

    @Override
    public List<Delivery> findAll(int begin, int end) throws DAOException {
        List<Delivery> deliveries = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, order_position_id, date, payment FROM `delivery` ORDER BY id LIMIT ?, ?")) {
            preparedStatement.setInt(1, begin);
            preparedStatement.setInt(2, end);
            ResultSet rs = preparedStatement.executeQuery();
            fill(deliveries, rs);
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return deliveries;
    }

    private void fill(List<Delivery> deliveries, ResultSet rs) throws SQLException {
        while (rs.next()) {
            int id = rs.getInt("id");
            OrderPosition orderPosition = new OrderPosition();
            int orderPositionId = rs.getInt("order_position_id");
            orderPosition.setId(orderPositionId);
            Date date = new Date(rs.getTimestamp("date").getTime());
            Delivery.Payment payment = Delivery.Payment.valueOf(rs.getString("payment").toUpperCase());
            deliveries.add(new Delivery(id, orderPosition, date, payment));
        }
    }

    @Override
    public Delivery findById(int id) throws DAOException {
        Delivery delivery = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT order_position_id, date, payment FROM `delivery` WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                OrderPosition orderPosition = new OrderPosition();
                int orderPositionId = rs.getInt("order_position_id");
                orderPosition.setId(orderPositionId);
                Date date = new Date(rs.getTimestamp("date").getTime());
                Delivery.Payment payment = Delivery.Payment.valueOf(rs.getString("payment").toUpperCase());
                delivery = new Delivery(id, orderPosition, date, payment);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return delivery;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM `delivery` WHERE id=?")) {
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Delivery delivery) throws DAOException {
        delete(delivery.getId());
        return true;
    }

    @Override
    public int create(Delivery delivery) throws DAOException {
        int id;
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO `delivery` (order_position_id, date, payment) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, delivery.getOrderPosition().getId());
            statement.setTimestamp(2, new Timestamp(delivery.getDate().getTime()));
            statement.setString(3, String.valueOf(delivery.getPayment()));
            statement.executeUpdate();
            id = findLastId(statement);
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return id;
    }

    @Override
    public boolean update(Delivery delivery) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE `delivery` SET date=?, payment=? WHERE id=?")) {
            preparedStatement.setTimestamp(1, new Timestamp(delivery.getDate().getTime()));
            preparedStatement.setString(2, String.valueOf(delivery.getPayment()));
            preparedStatement.setInt(3, delivery.getId());
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
    }

    public Delivery findByOrderPosition(OrderPosition orderPosition) throws DAOException {
        Delivery delivery = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, date, payment FROM `delivery` WHERE order_position_id=?")) {
            statement.setInt(1, orderPosition.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                Date date = new Date(rs.getTimestamp("date").getTime());
                Delivery.Payment payment = Delivery.Payment.valueOf(rs.getString("payment").toUpperCase());
                delivery = new Delivery(id, orderPosition, date, payment);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return delivery;
    }

    @Override
    public int countAll() throws DAOException {
        int count = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) as count FROM delivery");
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return count;
    }
}
