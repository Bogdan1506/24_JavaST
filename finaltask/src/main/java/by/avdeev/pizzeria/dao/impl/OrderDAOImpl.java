package by.avdeev.pizzeria.dao.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Order;
import by.avdeev.pizzeria.entity.Profile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static by.avdeev.pizzeria.command.ConstantRepository.COUNT;
import static by.avdeev.pizzeria.command.ConstantRepository.DATE;
import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.PROFILE_ID;

public class OrderDAOImpl extends AbstractDAO<Order> {
    @Override
    public List<Order> findAll(int begin, int end) throws DAOException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, profile_id, date FROM `order` ORDER BY id LIMIT ?, ?")) {
            preparedStatement.setInt(1, begin);
            preparedStatement.setInt(2, end);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                orders.add(fill(rs));
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return orders;
    }


    @Override
    public List<Order> findAll() throws DAOException {
        List<Order> orders = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(
                    "SELECT id, profile_id, date FROM `order`");
            while (rs.next()) {
                orders.add(fill(rs));
            }
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
                order = fill(rs);
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
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
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
                "INSERT INTO `order` (profile_id, date) VALUES (?,NOW())",
                Statement.RETURN_GENERATED_KEYS)) {
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
    public boolean update(Order order) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE `order` SET profile_id=?, date=? WHERE id=?")) {
            preparedStatement.setInt(1, order.getProfile().getId());
            preparedStatement.setTimestamp(2, new Timestamp(order.getDate().getTime()));
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
            String sql = "SELECT COUNT(*) as count FROM `order`";
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

    private Order fill(ResultSet rs) throws SQLException {
        Date date = new Date(rs.getTimestamp(DATE).getTime());
        return new Order(rs.getInt(ID),
                new Profile(rs.getInt(PROFILE_ID)),
                date);
    }
}
