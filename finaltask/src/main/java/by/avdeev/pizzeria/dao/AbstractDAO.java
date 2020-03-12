package by.avdeev.pizzeria.dao;

import by.avdeev.pizzeria.entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO<T extends Entity> {
    protected Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> findAll() throws DAOException;

    public abstract T findEntityById(int id);

    public abstract boolean delete(int id);

    public abstract boolean delete(T entity);

    public abstract boolean create(T entity);

    public abstract T update(T entity);

    public void close(Statement st) {   //TODO use it
        try {
            if (st != null) {
                st.close();
            System.out.println("WHHHHHHHHHYYYYYYYYYY");
            }
        } catch (SQLException e) {
        }
    }
}
