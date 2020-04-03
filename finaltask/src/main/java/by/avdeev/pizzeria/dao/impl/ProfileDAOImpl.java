package by.avdeev.pizzeria.dao.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAOImpl extends AbstractDAO<Profile> {

    public ProfileDAOImpl(Connection connection) {
        super(connection);
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
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                profiles.add(new Profile(id, user, name, surname, email, phone, address));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return profiles;
    }

    @Override
    public Profile findEntityById(int id) throws DAOException {
        Profile profile = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT user_id, name, surname, email, phone, address FROM profile WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                User user = new User();
                int userId = rs.getInt("user_id");
                user.setId(userId);
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                profile = new Profile(id, user, name, surname, email, phone, address);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return profile;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM profile WHERE user_id=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Profile entity) throws DAOException {
        delete(entity.getId());
        return true;
    }

    @Override
    public void create(Profile profile) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO profile (user_id, name, surname, email, phone, address) VALUES (?,?,?,?,?,?)")) {
            statement.setInt(1, profile.getUser().getId());
            statement.setString(2, profile.getName());
            statement.setString(3, profile.getSurname());
            statement.setString(4, profile.getEmail());
            statement.setString(5, profile.getPhone());
            statement.setString(6, profile.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Profile profile) throws DAOException {
        int id = profile.getUser().getId();
        String name = profile.getName();
        String surname = profile.getSurname();
        String email = profile.getEmail();
        String phone = profile.getPhone();
        String address = profile.getAddress();
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
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            profile = new Profile(id, name, surname, email, phone, address);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return profile;
    }
}
