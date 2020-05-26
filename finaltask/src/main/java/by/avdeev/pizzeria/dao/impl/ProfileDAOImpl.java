package by.avdeev.pizzeria.dao.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.avdeev.pizzeria.command.ConstantRepository.ADDRESS;
import static by.avdeev.pizzeria.command.ConstantRepository.EMAIL;
import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.NAME;
import static by.avdeev.pizzeria.command.ConstantRepository.PHONE;
import static by.avdeev.pizzeria.command.ConstantRepository.SURNAME;

public class ProfileDAOImpl extends AbstractDAO<Profile> {
    private static Logger logger = LogManager.getLogger(ProfileDAOImpl.class);

    @Override
    public List<Profile> findAll(final int begin,
                                 final int end) throws DAOException {
        List<Profile> profiles = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, name, surname, email, phone, address FROM profile LIMIT ?, ?")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Profile profile = fill(rs);
                profiles.add(profile);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return profiles;
    }

    @Override
    public List<Profile> findAll() throws DAOException {
        List<Profile> profiles = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(
                    "SELECT id, name, surname, email, phone, address FROM profile");
            while (rs.next()) {
                Profile profile = fill(rs);
                profiles.add(profile);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return profiles;
    }

    @Override
    public Profile findById(final int id) throws DAOException {
        Profile profile = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, name, surname, email, phone, address FROM profile WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                profile = fill(rs);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return profile;
    }

    @Override
    public boolean delete(final int id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM profile WHERE id=?")) {
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(final Profile profile) throws DAOException {
        delete(profile.getId());
        return true;
    }

    @Override
    public int create(final Profile profile) throws DAOException {
        int id;
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO profile (name, surname, email, phone, address)" +
                        "VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            fill(profile, statement);
            id = findLastId(statement);
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        return id;
    }

    @Override
    public boolean update(final Profile profile) throws DAOException {
        logger.debug("profile={}", profile);
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE profile SET name=?, surname=?, email=?, phone=?, address=? WHERE id=?")) {
            preparedStatement.setString(1, profile.getName());
            preparedStatement.setString(2, profile.getSurname());
            preparedStatement.setString(3, profile.getEmail());
            preparedStatement.setString(4, profile.getPhone());
            preparedStatement.setString(5, profile.getAddress());
            preparedStatement.setInt(6, profile.getId());
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
    }

    @Override
    public int countAll() {
        return 0;
    }

    public Profile findByUserLogin(final String login) throws DAOException {
        Profile profile = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT profile.id, name, surname, phone, email, address from profile JOIN `user` ON user.profile_id = profile.id WHERE user.login=?")) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                profile = fill(rs);
            }
        } catch (SQLException e) {
            rollback();
            throw new DAOException(e);
        }
        logger.debug("profile={}", profile);
        return profile;
    }

    private Profile fill(final ResultSet rs) throws SQLException {
        int id = rs.getInt(ID);
        String name = rs.getString(NAME);
        String surname = rs.getString(SURNAME);
        String email = rs.getString(EMAIL);
        String phone = rs.getString(PHONE);
        String address = rs.getString(ADDRESS);
        return new Profile(id, name, surname, email, phone, address);
    }


    private void fill(final Profile profile,
                      final PreparedStatement statement)
            throws SQLException {
        statement.setString(1, profile.getName());
        statement.setString(2, profile.getSurname());
        statement.setString(3, profile.getEmail());
        statement.setString(4, profile.getPhone());
        statement.setString(5, profile.getAddress());
        statement.executeUpdate();
    }
}
