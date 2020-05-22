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
     */
    public TransactionFactoryImpl() throws ServiceException {
        this.connection = ConnectionPoolImpl.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

        } catch (SQLException e) {
            throw new ServiceException();
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
    public void close() throws Exception {
//        ConnectionPool conPool = ConnectionPoolImpl.getInstance();
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
//        conPool.releaseConnection(connection);
    }
}
