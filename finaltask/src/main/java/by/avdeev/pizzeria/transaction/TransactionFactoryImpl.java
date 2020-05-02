package by.avdeev.pizzeria.transaction;

import by.avdeev.pizzeria.dao.pool.ConnectionPool;
import by.avdeev.pizzeria.dao.pool.ConnectionPoolImpl;
import by.avdeev.pizzeria.service.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactoryImpl implements TransactionFactory {
    private Connection connection;

    public TransactionFactoryImpl() throws ServiceException {
        ConnectionPool connectionPool = ConnectionPoolImpl.getConnectionPoolImpl();
        connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Transaction createTransaction() {
        return new TransactionImpl(connection);
    }

    @Override
    public void close() throws ServiceException {
        ConnectionPool connectionPool = ConnectionPoolImpl.getConnectionPoolImpl();
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
        connectionPool.releaseConnection(connection);
    }
}
