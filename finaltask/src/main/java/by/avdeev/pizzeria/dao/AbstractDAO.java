package by.avdeev.pizzeria.dao;

import by.avdeev.pizzeria.entity.Entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public abstract boolean delete(T entity) throws DAOException;

    public abstract void create(T entity) throws DAOException;

    public abstract void update(T entity) throws DAOException;

    public int findLastInsertId() throws DAOException {
        int lastId = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet lastIdSet = statement.executeQuery("SELECT LAST_INSERT_ID()");
            if (lastIdSet.next()) {
                lastId = lastIdSet.getInt("last_insert_id()");
            }

        } catch (
                SQLException e) {
            throw new DAOException(e);
        }
        return lastId;
    }
}
