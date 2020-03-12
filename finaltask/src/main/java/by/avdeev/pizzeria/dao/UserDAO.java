package by.avdeev.pizzeria.dao;

import by.avdeev.pizzeria.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    public UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM user";
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
        return users;
    }

    @Override
    public User findEntityById(int id) {
        User user = new User();
        user.setId(id);
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE id=?")) {
            //todo change on all fields
            statement.setString(1, String.valueOf(id)); //todo
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public boolean create(User entity) {
        String login = entity.getLogin();
        String password = entity.getPassword();
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO user (login, password, role) VALUES (?,?,?)")) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setInt(3, 3);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User update(User entity) {
        int id = entity.getId();
        String password = entity.getPassword();
        int role = entity.getRole();
        PreparedStatement statement = null; //todo try-with-res
        try {
            statement = connection.prepareStatement("UPDATE user SET password=?, role=? WHERE id=?");
            statement.setString(1, password);
            statement.setInt(2, role);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return entity; //todo remove return value
    }
}
