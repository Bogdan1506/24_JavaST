package by.avdeev.pizzeria.dao.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolImpl implements ConnectionPool {
    private static ConnectionPoolImpl connectionPoolImpl;

    private static Logger logger = LogManager.getLogger(ConnectionPoolImpl.class);

    private ConnectionPoolImpl() {
    }

    public static ConnectionPoolImpl getInstance() {
        if (connectionPoolImpl == null) {
            connectionPoolImpl = new ConnectionPoolImpl();
        }
        return connectionPoolImpl;
    }

    public Connection getConnection() {
        Context ctx;
        Connection connection = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/pizzeriaPool");
            connection = ds.getConnection();
        } catch (NamingException | SQLException e) {
            logger.error(e);
        }
        return connection;
    }
}