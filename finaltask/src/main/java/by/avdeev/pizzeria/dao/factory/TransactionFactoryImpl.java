package by.avdeev.pizzeria.dao.factory;

import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.TransactionImpl;
import by.avdeev.pizzeria.dao.pool.ConnectionPool;
import by.avdeev.pizzeria.dao.Transaction;
import by.avdeev.pizzeria.dao.pool.another.BasicConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactoryImpl implements TransactionFactory {
    private Connection connection;
    private BasicConnectionPool connectionPool;

    public TransactionFactoryImpl() {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool = BasicConnectionPool.getBasicConnectionPool();
        connection = connectionPool.getConnection();
    }

    /*    public TransactionFactoryImpl() throws DAOException {
        if (connection == null) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
        }
    }*/

    @Override
    public void close() {
        System.out.println("v close connection = " + connection);
        //            connection.close();
        connectionPool.releaseConnection(connection);
    }

    @Override
    public Transaction createTransaction() {
        return new TransactionImpl(connection);
    }
}
