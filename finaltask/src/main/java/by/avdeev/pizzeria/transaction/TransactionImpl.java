package by.avdeev.pizzeria.transaction;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.entity.Entity;

import java.sql.Connection;

/**
 * Implementation of ${@link Transaction}.
 */
public class TransactionImpl implements Transaction {
    /**
     * Instance of ${@link Connection}.
     */
    private Connection connection;

    /**
     * @param connection Instance of ${@link Connection}.
     */
    public TransactionImpl(final Connection connection) {
        this.connection = connection;
    }


    /**
     * Creates ${@link AbstractDAO} with determined ${@link Type}.
     *
     * @param type ${@link Type}.
     * @param <T>  Implementing instance of ${@link Entity}.
     * @return Instance of ${@link AbstractDAO}.
     */
    @Override
    public <T extends Entity> AbstractDAO<T> createDao(final Type type) {
        DAOTypeProvider daoTypeProvider = DAOTypeProvider.getDaoTypeProvider();
        @SuppressWarnings("unchecked")
        AbstractDAO<T> dao = (AbstractDAO<T>) daoTypeProvider.findDAO(type);
        dao.setConnection(connection);
        return dao;
    }

}
