package by.avdeev.pizzeria;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final String DATASOURCE_NAME = "jdbc/billingDB";
    private static DataSource dataSource;
    static {
        try {
            Context initContext = new InitialContext();
//            Context envContext = (Context) initContext.lookup("java:/comp/env"); //todo
            dataSource = (DataSource) initContext.lookup(DATASOURCE_NAME);
        } catch (NamingException e) {
            e.printStackTrace();  //todo
        }
    }
    private ConnectionPool() { }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
// метод возвращения Connection в пул
}