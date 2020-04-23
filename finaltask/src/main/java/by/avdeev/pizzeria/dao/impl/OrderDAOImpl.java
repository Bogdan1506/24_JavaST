package by.avdeev.pizzeria.dao.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Order;
import by.avdeev.pizzeria.entity.Profile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class OrderDAOImpl extends AbstractDAO<Order> {
    @Override
    public List<Order> findAll(int begin, int end) throws DAOException {
        return null;
    }

    @Override
    public List<Order> findAll() throws DAOException {
        List<Order> orders = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT id, profile_id, date FROM `order`");
            while (rs.next()) {
                Order order = null;
                int id = rs.getInt("id");
                Profile profile = new Profile();
                int profileId = rs.getInt("profile_id");
                profile.setId(profileId);
                Date date = rs.getDate("date");
                orders.add(new Order(id, profile, date));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return orders;
    }

    @Override
    public Order findById(int id) throws DAOException {
        Order order = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT profile_id, date FROM `order` WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Profile profile = new Profile();
                int profileId = rs.getInt("profile_id");
                profile.setId(profileId);
                Date date = rs.getDate("date");
                order = new Order(id, profile, date);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return order;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM `order` WHERE id=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Order order) throws DAOException {
        delete(order.getId());
        return true;
    }

    @Override
    public void create(Order order) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO `order` (profile_id, date) VALUES (?,?)")) {
            statement.setInt(1, order.getProfile().getId());
            statement.setDate(2, order.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Order order) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE `order` SET profile_id=?, date=? WHERE id=?")) {
            statement.setInt(1, order.getProfile().getId());
            statement.setDate(2, order.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
