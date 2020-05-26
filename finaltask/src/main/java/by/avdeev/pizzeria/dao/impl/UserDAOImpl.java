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

import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.LOGIN;
import static by.avdeev.pizzeria.command.ConstantRepository.PASS;
import static by.avdeev.pizzeria.command.ConstantRepository.PROFILE_ID;
import static by.avdeev.pizzeria.command.ConstantRepository.ROLE;

public class UserDAOImpl extends AbstractDAO<User> {
    private static Logger logger = LogManager.getLogger(UserDAOImpl.class);

    @Override
    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<>();
        try {
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
    public List<User> findAll(final int begin,
                              final int end) throws DAOException {
        logger.debug("begin={}, end={}", begin, end);
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, login, password, profile_id, role FROM user ORDER BY id LIMIT ?, ?")) {
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

    @Override
    public User findById(final int id) throws DAOException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT login, password, profile_id, role FROM user WHERE id=?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User(id);
                user.setLogin(rs.getString(LOGIN));
                user.setPassword(rs.getString(PASS));
                user.setProfile(new Profile(rs.getInt(PROFILE_ID)));
                user.setRole(Role.getByIdentity(rs.getInt(ROLE)));
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return user;
    }

    @Override
    public boolean delete(final int id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM user WHERE id=?")) {
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(final User user) throws DAOException {
        delete(user.getId());
        return true;
    }

    @Override
    public int create(final User user) throws DAOException {
        int id;
        String login = user.getLogin();
        String password = user.getPassword();
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO user (login, password) VALUES (?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
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
    public boolean update(final User user) throws DAOException {
        logger.debug("user={}", user);
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE user SET password=?, role=?, profile_id=? WHERE id=?")) {
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

    public User findByLogin(final String login) throws DAOException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, password, profile_id, role FROM user WHERE login=?")) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(ID));
                user.setLogin(login);
                user.setPassword(rs.getString(PASS));
                user.setProfile(new Profile(rs.getInt(PROFILE_ID)));
                user.setRole(Role.getByIdentity(rs.getInt(ROLE)));
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return user;
    }

    public boolean changeRole(final Role role,
                              final int id) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE user SET role = ? WHERE id=?")) {
            statement.setInt(1, role.getId());
            statement.setInt(2, id);
            int rows = statement.executeUpdate();
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

    private void fill(final List<User> users,
                      final ResultSet rs) throws SQLException {
        while (rs.next()) {
            int id = rs.getInt(ID);
            String login = rs.getString(LOGIN);
            String password = rs.getString(PASS);
            Role role = Role.getByIdentity(rs.getInt(ROLE));
            User user = new User(id, login, password,
                    new Profile(rs.getInt(PROFILE_ID)), role);
            users.add(user);
        }
    }
}
