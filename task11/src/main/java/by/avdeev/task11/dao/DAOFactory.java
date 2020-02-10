package by.avdeev.task11.dao;

public class DAOFactory {
    private static final DAOFactory factory = new DAOFactory();
    private final TextDAO textDAO = new TextDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getFactory() {
        return factory;
    }

    public TextDAO getTextDAO() {
        return textDAO;
    }
}
