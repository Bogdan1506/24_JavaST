package by.avdeev.pizzeria.dao.pool.another;

import by.avdeev.pizzeria.dao.DAOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;

public class BasicConnectionPool
        implements ConnectionPool {

    //    private List<Connection> connectionPool;
//    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 5;
    private static BasicConnectionPool basicConnectionPool;
    private BlockingQueue<Connection> freeConnections = new LinkedBlockingQueue<>();
    private Set<Connection> usedConnections = new HashSet<>();
//    private Set<Connection> usedConnections = new ConcurrentSkipListSet<>();

    private BasicConnectionPool() {
    }

    public static BasicConnectionPool getBasicConnectionPool() {
        if (basicConnectionPool == null) {
            basicConnectionPool = create();
        }
        return basicConnectionPool;
    }

    public static BasicConnectionPool create() {
//        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        BlockingQueue<Connection> freeConnections = new LinkedBlockingQueue<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
//            pool.add(createConnection());
            freeConnections.add(createConnection());
        }
        return new BasicConnectionPool(freeConnections);
//        return new BasicConnectionPool(pool);
    }

    /*public BasicConnectionPool(List<Connection> connectionPool) {
        this.connectionPool = connectionPool;
    }*/
    public BasicConnectionPool(BlockingQueue<Connection> freeConnections) {
        this.freeConnections = freeConnections;
    }

// standard constructors

    @Override
    public Connection getConnection() {
   /*     System.out.println("conPool size=" + connectionPool.size() + " connectionPool = " + connectionPool);
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);*/
        Connection connection = null;
        try {
            connection = freeConnections.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("beret connection = " + connection);
        ;
        usedConnections.add(connection);
        System.out.println("Used size=" + usedConnections.size() + " usedConnections = " + usedConnections);

        System.out.println("pool connection = " + connection);

        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        try {
            System.out.println("v release connection = " + connection.unwrap(Connection.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myPool");
            connection = ds.getConnection();
        } catch (NamingException | SQLException e) {
//            throw new DAOException(e);
        }
        return connection;
//        return DriverManager.getConnection(url, user, password);
    }

//    public int getSize() {
//        return connectionPool.size() + usedConnections.size();
//    }

    // standard getters
}