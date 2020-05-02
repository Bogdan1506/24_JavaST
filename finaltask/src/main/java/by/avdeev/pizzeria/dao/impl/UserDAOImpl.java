package by.avdeev.pizzeria.dao.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.Order;
import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractDAO<User> {
    private static Logger logger = LogManager.getLogger();
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<>();
        try {
            logger.debug(String.format("Connection=%s", connection));
            Statement statement = connection.createStatement();
            String sql = "SELECT id, login, password, role FROM user";
            ResultSet rs = statement.executeQuery(sql);
            fill(users, rs);
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return users;
    }

    @Override
    public List<User> findAll(int begin, int end) throws DAOException {
        logger.debug("begin={}, end={}", begin, end);
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, login, password, role FROM user ORDER BY id LIMIT ?, ?")) {
            preparedStatement.setInt(1, begin);
            preparedStatement.setInt(2, end);
            ResultSet rs = preparedStatement.executeQuery();
            fill(users, rs);
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return users;
    }

    private void fill(List<User> users, ResultSet rs) throws SQLException {
        while (rs.next()) {
            int id = rs.getInt("id");
            String login = rs.getString(LOGIN);
            String password = rs.getString(PASSWORD);
            int roleInt = rs.getInt("role");
            Role role = Role.getByIdentity(roleInt);
            User user = new User(id, login, password, role);
            users.add(user);
        }
    }

    @Override
    public User findById(int id) throws DAOException {
        User user = new User();
        user.setId(id);
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT login, password, role FROM user WHERE id=?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user.setLogin(rs.getString(LOGIN));
                user.setPassword(rs.getString(PASSWORD));
                int roleInt = rs.getInt("role");
                Role role = Role.getByIdentity(roleInt);
                user.setRole(role);
            }
        } catch (SQLException e) {
            rollback();
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
            rollback();
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(User user) throws DAOException {
        delete(user.getId());
        return true;
    }

    @Override
    public void create(User user) throws DAOException {
        String login = user.getLogin();
        String password = user.getPassword();
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO user (login, password) VALUES (?,?)")) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        int id = user.getId();
        String password = user.getPassword();
        Role role = user.getRole();
        logger.debug(String.format("role=%s", role));
        int roleInt = role.getId();
        try (PreparedStatement statement = connection.prepareStatement("UPDATE user SET password=?, role=? WHERE id=?")) {
            statement.setString(1, password);
            statement.setInt(2, roleInt);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
    }

    public User findByLogin(String login) throws DAOException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, login, password, role FROM user WHERE login=?")) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                login = rs.getString(LOGIN);
                if (login != null) {
                    user = new User();
                    user.setLogin(login);
                    user.setId(rs.getInt("id"));
                    user.setPassword(rs.getString(PASSWORD));
                    int roleInt = rs.getInt("role");
                    Role role = Role.getByIdentity(roleInt);
                    user.setRole(role);
                }
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return user;
    }

    public void changeRole(Role role, int id) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE user SET role = ? WHERE id=?")) {
            statement.setInt(1, role.getId());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
    }
}
