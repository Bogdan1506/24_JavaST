package by.avdeev.pizzeria.transaction;

import by.avdeev.pizzeria.dao.pool.ConnectionPool;
import by.avdeev.pizzeria.dao.pool.ConnectionPoolImpl;

import java.sql.Connection;

public class TransactionFactoryImpl implements TransactionFactory {
    private Connection connection;

    public TransactionFactoryImpl() {
        ConnectionPool connectionPool = ConnectionPoolImpl.getConnectionPoolImpl();
        connection = connectionPool.getConnection();
    }

    @Override
    public Transaction createTransaction() {
        return new TransactionImpl(connection);
    }

    @Override
    public void close() {
        ConnectionPool connectionPool = ConnectionPoolImpl.getConnectionPoolImpl();
        connectionPool.releaseConnection(connection);
    }
}
