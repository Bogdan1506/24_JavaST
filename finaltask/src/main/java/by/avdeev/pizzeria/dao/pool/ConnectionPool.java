package by.avdeev.pizzeria.dao.pool;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection();
}