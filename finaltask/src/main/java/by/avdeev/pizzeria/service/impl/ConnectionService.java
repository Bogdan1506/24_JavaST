package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.pool.ConnectionPool;
import by.avdeev.pizzeria.dao.pool.ConnectionPoolImpl;
import by.avdeev.pizzeria.service.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionService {
    private ConnectionPool connectionPool = ConnectionPoolImpl.getConnectionPoolImpl();


    public Connection createConnection() throws ServiceException {
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        connectionPool.releaseConnection(connection);
    }
}

