package by.avdeev.pizzeria.dao;

import by.avdeev.pizzeria.entity.Entity;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDAO<T extends Entity> {
    protected Connection connection;

    public AbstractDAO() {
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> findAll() throws DAOException;

    public abstract T findEntityById(int id) throws DAOException;

    public abstract boolean delete(int id) throws DAOException;

    public abstract boolean delete(T entity);

    public abstract boolean create(T entity) throws DAOException;

    public abstract void update(T entity) throws DAOException;
}
