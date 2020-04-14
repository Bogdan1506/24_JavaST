package by.avdeev.pizzeria.dao.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.entity.Order;
import by.avdeev.pizzeria.entity.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAOImpl extends AbstractDAO<Delivery> {
    @Override
    public List<Delivery> findAll() throws DAOException {
        List<Delivery> deliveries = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT id, order_id, date, payment FROM `delivery`");
            while (rs.next()) {
                int id = rs.getInt("id");
                Order order = new Order();
                int orderId = rs.getInt("order_id");
                order.setId(orderId);
                Date date = rs.getDate("date");
                Delivery.Payment payment = Delivery.Payment.valueOf(rs.getString("payment").toUpperCase());
                deliveries.add(new Delivery(id, order, date, payment));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return deliveries;
    }

    @Override
    public Delivery findById(int id) throws DAOException {
        Delivery delivery = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT order_id, date, payment FROM `delivery` WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Order order = new Order();
                int orderId = rs.getInt("order_id");
                order.setId(orderId);
                Date date = rs.getDate("date");
                Delivery.Payment payment = Delivery.Payment.valueOf(rs.getString("payment").toUpperCase());
                delivery = new Delivery(id, order, date, payment);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return delivery;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM `delivery` WHERE id=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Delivery delivery) throws DAOException {
        delete(delivery.getId());
        return true;
    }

    @Override
    public void create(Delivery delivery) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO `delivery` (order_id, date, payment) VALUES (?,?,?)")) {
            statement.setInt(1, delivery.getOrder().getId());
            statement.setDate(2, delivery.getDate());
            statement.setString(3, String.valueOf(delivery.getPayment()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Delivery delivery) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE `delivery` SET order_id=?, date=?, payment=? WHERE id=?")) {
            statement.setInt(1, delivery.getOrder().getId());
            statement.setDate(2, delivery.getDate());
            statement.setString(3, String.valueOf(delivery.getPayment()));
            statement.setInt(4, delivery.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
