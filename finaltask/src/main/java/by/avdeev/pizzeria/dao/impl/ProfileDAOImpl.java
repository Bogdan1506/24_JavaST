package by.avdeev.pizzeria.dao.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAOImpl extends AbstractDAO<Profile> {
    private static Logger logger = LogManager.getLogger();

    @Override
    public List<Profile> findAll(int begin, int end) throws DAOException {
        return null;
    }

    @Override
    public List<Profile> findAll() throws DAOException {
        List<Profile> profiles = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT user_id, id, name, surname, email, phone, address FROM profile");
            while (rs.next()) {
                User user = new User();
                int userId = rs.getInt("user_id");
                user.setId(userId);
                int id = rs.getInt("id");
                Profile profile = fill(rs, id);
                profile.setUser(user);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return profiles;
    }

    @Override
    public Profile findById(int id) throws DAOException {
        Profile profile = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT user_id, name, surname, email, phone, address FROM profile WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                User user = new User();
                int userId = rs.getInt("user_id");
                user.setId(userId);
                profile = fill(rs, id);
                profile.setUser(user);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return profile;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM profile WHERE id=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Profile profile) throws DAOException {
        delete(profile.getId());
        return true;
    }

    @Override
    public void create(Profile profile) throws DAOException {
        logger.debug("profile={}", profile);
        if (profile.getUser() != null) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO profile (name, surname, email, phone, address, user_id) VALUES (?,?,?,?,?,?)")) {
                statement.setInt(6, profile.getUser().getId());
                fill(profile, statement);
            } catch (SQLException e) {
                rollback();
                throw new DAOException(e);
            }
        } else {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO profile (name, surname, email, phone, address) VALUES (?,?,?,?,?)")) {
                fill(profile, statement);
            } catch (SQLException e) {
                rollback();
                throw new DAOException(e);
            }
        }
    }

    private void fill(Profile profile, PreparedStatement statement) throws SQLException {
        statement.setString(1, profile.getName());
        statement.setString(2, profile.getSurname());
        statement.setString(3, profile.getEmail());
        statement.setString(4, profile.getPhone());
        statement.setString(5, profile.getAddress());
        statement.executeUpdate();
    }

    @Override
    public void update(Profile profile) throws DAOException {
        logger.debug("profile={}", profile);
        int id = profile.getUser().getId();
        String name = profile.getName();
        String surname = profile.getSurname();
        String email = profile.getEmail();
        String phone = profile.getPhone();
        String address = profile.getAddress();
        logger.debug(String.format("Connection=%s", connection));
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE profile SET name=?, surname=?, email=?, phone=?, address=? WHERE user_id=?")) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, email);
            statement.setString(4, phone);
            statement.setString(5, address);
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
    }

    public Profile findByUserId(int userId) throws DAOException {
        Profile profile;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id, name, surname, email, phone, address FROM profile WHERE user_id=?")) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            rs.next();
            profile = fill(rs, userId);
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return profile;
    }

    private Profile fill(ResultSet rs, int id) throws SQLException {
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String address = rs.getString("address");
        return new Profile(id, name, surname, email, phone, address);
    }
}
