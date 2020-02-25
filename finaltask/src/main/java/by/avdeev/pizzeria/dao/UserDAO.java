package by.avdeev.pizzeria.dao;

import by.avdeev.pizzeria.entity.User;

import java.sql.Connection;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    public UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findEntityById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public User update(User entity) {
        return null;
    }
}
