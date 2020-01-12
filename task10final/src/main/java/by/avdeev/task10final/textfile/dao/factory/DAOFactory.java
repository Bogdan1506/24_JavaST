package by.avdeev.task10final.textfile.dao.factory;

import by.avdeev.task10final.textfile.dao.TextFileDAO;
import by.avdeev.task10final.textfile.dao.impl.TextFileDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory = new DAOFactory();

    private final TextFileDAO textFileDAO = new TextFileDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory;
    }

    public TextFileDAO getTextFileDAO() {
        return textFileDAO;
    }
}
