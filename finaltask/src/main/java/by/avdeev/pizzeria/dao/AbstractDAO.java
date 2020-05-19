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

    public abstract List<T> findAll(int begin, int end) throws DAOException;

    public abstract T findById(int id) throws DAOException;

    public abstract boolean delete(int id) throws DAOException;

    public abstract boolean delete(T entity) throws DAOException;

    public abstract int create(T entity) throws DAOException;

    public abstract boolean update(T entity) throws DAOException;

    public abstract int countAll() throws DAOException;

    public int findLastId(PreparedStatement ps) throws SQLException {
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
