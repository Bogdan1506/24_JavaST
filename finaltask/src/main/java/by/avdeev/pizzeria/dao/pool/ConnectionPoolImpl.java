package by.avdeev.pizzeria.dao.pool;

import by.avdeev.pizzeria.dao.DAOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPoolImpl
        implements ConnectionPool {
    private static int INITIAL_POOL_SIZE = 5;
    private static ConnectionPoolImpl connectionPoolImpl;
    private Queue<Connection> freeConnections = new ConcurrentLinkedQueue<>();
    private Set<Connection> usedConnections = new HashSet<>();

    private ConnectionPoolImpl() {
    }

    public static ConnectionPoolImpl getConnectionPoolImpl() {
        if (connectionPoolImpl == null) {
            connectionPoolImpl = create();
        }
        return connectionPoolImpl;
    }

    public static ConnectionPoolImpl create() {
        BlockingQueue<Connection> freeConnections = new LinkedBlockingQueue<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            freeConnections.add(createConnection());
        }
        return new ConnectionPoolImpl(freeConnections);
    }

    public ConnectionPoolImpl(BlockingQueue<Connection> freeConnections) {
        this.freeConnections = freeConnections;
    }

    @Override
    public Connection getConnection() {
        Connection connection;
        connection = freeConnections.poll();
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        freeConnections.add(connection);
        return usedConnections.remove(connection);
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public String getUser() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    private static Connection createConnection() {
        Context ctx;
        Connection connection = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/pizzeriaPool");
            connection = ds.getConnection();
        } catch (NamingException | SQLException e) {
//            throw new DAOException(e);
        }
        return connection;
    }
}