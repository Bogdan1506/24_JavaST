package by.avdeev.pizzeria.dao;

import by.avdeev.pizzeria.entity.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<T extends Entity> {
    protected Connection connection;

    public AbstractDAO() {
    }

    public void setConnection(final Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public AbstractDAO(final Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> findAll() throws DAOException;

    public abstract List<T> findAll(final int begin, final int end) throws DAOException;

    public abstract T findById(final int id) throws DAOException;

    public abstract boolean delete(final int id) throws DAOException;

    public abstract boolean delete(final T entity) throws DAOException;

    public abstract int create(final T entity) throws DAOException;

    public abstract boolean update(final T entity) throws DAOException;

    public abstract int countAll() throws DAOException;

    public int findLastId(final PreparedStatement ps) throws SQLException {
        int lastId = 0;
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            lastId = rs.getInt(1);
        }
        return lastId;
    }

    protected void rollback() throws DAOException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
