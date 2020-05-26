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

import static by.avdeev.pizzeria.command.ConstantRepository.COUNT;
import static by.avdeev.pizzeria.command.ConstantRepository.DATE;
import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.PAYMENT;

public class DeliveryDAOImpl extends AbstractDAO<Delivery> {
    @Override
    public List<Delivery> findAll() throws DAOException {
        List<Delivery> deliveries = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(
                    "SELECT id, order_position_id, date, payment FROM `delivery`");
            while (rs.next()) {
                fill(rs);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return deliveries;
    }

    @Override
    public List<Delivery> findAll(final int begin,
                                  final int end) throws DAOException {
        List<Delivery> deliveries = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, order_position_id, date, payment FROM `delivery` ORDER BY id LIMIT ?, ?")) {
            preparedStatement.setInt(1, begin);
            preparedStatement.setInt(2, end);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                deliveries.add(fill(rs));
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return deliveries;
    }

    @Override
    public Delivery findById(final int id) throws DAOException {
        Delivery delivery = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, order_position_id, date, payment FROM `delivery` WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                delivery = fill(rs);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return delivery;
    }

    @Override
    public boolean delete(final int id) throws DAOException {
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
    public boolean delete(final Delivery delivery) throws DAOException {
        delete(delivery.getId());
        return true;
    }

    @Override
    public int create(final Delivery delivery) throws DAOException {
        int id;
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO `delivery` (order_position_id, date, payment) VALUES (?,?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
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
    public boolean update(final Delivery delivery) throws DAOException {
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

    @Override
    public int countAll() throws DAOException {
        int count = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(
                    "SELECT COUNT(*) AS count FROM delivery");
            if (rs.next()) {
                count = rs.getInt(COUNT);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return count;
    }

    public int findCountByDate(final java.sql.Date firstDate,
                               final java.sql.Date secondDate)
            throws DAOException {
        int count = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(*) AS count FROM delivery WHERE date >= ? AND date < ?")) {
            preparedStatement.setDate(1, firstDate);
            preparedStatement.setDate(2, secondDate);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(COUNT);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return count;
    }

    private Delivery fill(final ResultSet rs) throws SQLException {
        Date date = new Date(rs.getTimestamp(DATE).getTime());
        Delivery.Payment payment = Delivery.Payment.valueOf(rs.getString(PAYMENT).toUpperCase());
        return new Delivery(rs.getInt(ID),
                new OrderPosition(rs.getInt(ID)),
                date, payment);
    }
}
