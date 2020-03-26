package by.avdeev.pizzeria.dao.pool;

import by.avdeev.pizzeria.dao.DAOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static ConnectionPool instance = new ConnectionPool();

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection() throws DAOException {
        Context ctx;
        Connection connection;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myPool");
            connection = ds.getConnection();
        } catch (NamingException | SQLException e) {
            throw new DAOException(e);
        }
        return connection;
    }
}
