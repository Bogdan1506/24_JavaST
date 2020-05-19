package by.avdeev.pizzeria.dao.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Profile;
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
            logger.debug("Connection={}", connection);
            Statement statement = connection.createStatement();
            String sql = "SELECT id, login, password, profile_id, role FROM user";
            ResultSet rs = statement.executeQuery(sql);
            fill(users, rs);
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        logger.debug("users={}", users);
        return users;
    }

    @Override
    public List<User> findAll(int begin, int end) throws DAOException {
        logger.debug("begin={}, end={}", begin, end);
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, login, password, profile_id, role FROM user ORDER BY id LIMIT ?, ?")) {
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
            Profile profile = new Profile();
            profile.setId(rs.getInt("profile_id"));
            User user = new User(id, login, password, profile, role);
            users.add(user);
        }
    }

    @Override
    public User findById(int id) throws DAOException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT login, password, profile_id, role FROM user WHERE id=?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(id);
                user.setLogin(rs.getString(LOGIN));
                user.setPassword(rs.getString(PASSWORD));
                Profile profile = new Profile();
                profile.setId(rs.getInt("profile_id"));
                user.setProfile(profile);
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id=?")) {
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
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
    public int create(User user) throws DAOException {
        int id;
        String login = user.getLogin();
        String password = user.getPassword();
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO user (login, password) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.executeUpdate();
            id = findLastId(statement);
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return id;
    }

    @Override
    public boolean update(User user) throws DAOException {
        logger.debug("user={}", user);
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET password=?, role=?, profile_id=? WHERE id=?")) {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setInt(2, user.getRole().getId());
            preparedStatement.setInt(3, user.getProfile().getId());
            preparedStatement.setInt(4, user.getId());
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
    }

    public User findByLogin(String login) throws DAOException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, password, profile_id, role FROM user WHERE login=?")) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setLogin(login);
                user.setId(rs.getInt("id"));
                user.setPassword(rs.getString(PASSWORD));
                int roleInt = rs.getInt("role");
                Role role = Role.getByIdentity(roleInt);
                Profile profile = new Profile();
                profile.setId(rs.getInt("profile_id"));
                user.setProfile(profile);
                user.setRole(role);
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

    public int countAll() throws DAOException {
        int total = 0;
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT COUNT(*) as count FROM user";
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
