package by.avdeev.pizzeria.dao.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractDAO<User> {
    public UserDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void setConnection(Connection connection) {
        super.setConnection(connection);
    }

    @Override
    public List<User> findAll() throws DAOException {
        System.out.println("dao connection = " + connection);

        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT id, login, password, role FROM user";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                int role = rs.getInt("role");
                User user = new User(id, login, password, role);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        System.out.println("users = " + users);
        return users;
    }

    @Override
    public User findEntityById(int id) throws DAOException {
        User user = new User();
        user.setId(id);
        try (PreparedStatement statement = connection.prepareStatement("SELECT login, password, role FROM user WHERE id=?")) {
            statement.setString(1, String.valueOf(id)); //todo
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public boolean create(User user) throws DAOException {
        String login = user.getLogin();
        String password = user.getPassword();
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO user (login, password, role) VALUES (?,?,?)")) {  //todo default value in mysql
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setInt(3, 3);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        int id = user.getId();
        String password = user.getPassword();
        int role = user.getRole();
        try (PreparedStatement statement = connection.prepareStatement("UPDATE user SET password=?, role=? WHERE id=?");) {
            statement.setString(1, password);
            statement.setInt(2, role);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
