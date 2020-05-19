package by.avdeev.pizzeria.transaction;

import by.avdeev.pizzeria.dao.pool.ConnectionPool;
import by.avdeev.pizzeria.dao.pool.ConnectionPoolImpl;
import by.avdeev.pizzeria.service.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Implementation of ${@link TransactionFactory}.
 */
public class TransactionFactoryImpl implements TransactionFactory {
    /**
     * Connection to database.
     */
    private Connection connection;

    /**
     * Public constructor that gets ${@link Connection}
     * from ${@link ConnectionPool}.
     *
     * @throws ServiceException If ${@link SQLException} occurs.
     */
    public TransactionFactoryImpl() throws ServiceException {
        ConnectionPool conPool = ConnectionPoolImpl.getConnectionPoolImpl();
        connection = conPool.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Getting new instance of ${@link TransactionImpl} with connection.
     *
     * @return instance ${@link Transaction}.
     */
    @Override
    public Transaction createTransaction() {
        return new TransactionImpl(connection);
    }

    /**
     * Commits and closes ${@link Connection}.
     *
     * @throws ServiceException If ${@link SQLException} occurs.
     */
    @Override
    public void close() throws ServiceException {
        ConnectionPool conPool = ConnectionPoolImpl.getConnectionPoolImpl();
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
        conPool.releaseConnection(connection);
    }
}
