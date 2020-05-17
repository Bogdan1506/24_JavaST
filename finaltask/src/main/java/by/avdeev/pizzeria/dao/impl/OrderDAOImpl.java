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
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, profile_id, date FROM `order` ORDER BY id LIMIT ?, ?")) {
            preparedStatement.setInt(1, begin);
            preparedStatement.setInt(2, end);
            ResultSet rs = preparedStatement.executeQuery();
            fill(orders, rs);
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return orders;
    }

    private void fill(List<Order> orders, ResultSet rs) throws SQLException {
        while (rs.next()) {
            int id = rs.getInt("id");
            Profile profile = new Profile();
            int profileId = rs.getInt("profile_id");
            profile.setId(profileId);
            Date date = rs.getDate("date");
            orders.add(new Order(id, profile, date));
        }
    }

    @Override
    public List<Order> findAll() throws DAOException {
        List<Order> orders = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT id, profile_id, date FROM `order`");
            fill(orders, rs);
        } catch (SQLException e) {
            rollback();
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
            rollback();
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
            rollback();
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Order order) throws DAOException {
        delete(order.getId());
        return true;
    }

    @Override
    public int create(Order order) throws DAOException {
        int id;
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO `order` (profile_id, date) VALUES (?,NOW())", Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, order.getProfile().getId());
            statement.executeUpdate();
            id = findLastId(statement);
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return id;
    }

    @Override
    public void update(Order order) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE `order` SET profile_id=?, date=? WHERE id=?")) {
            statement.setInt(1, order.getProfile().getId());
            statement.setDate(2, order.getDate());
            statement.executeUpdate();
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
            String sql = "SELECT COUNT(*) as count FROM `order`";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                total = rs.getInt("count");
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return total;
    }
}
