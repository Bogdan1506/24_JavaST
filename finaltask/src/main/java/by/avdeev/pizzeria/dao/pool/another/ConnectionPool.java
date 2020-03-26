package by.avdeev.pizzeria.dao.pool.another;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection();
    boolean releaseConnection(Connection connection);
    String getUrl();
    String getUser(); //todo
    String getPassword();
}