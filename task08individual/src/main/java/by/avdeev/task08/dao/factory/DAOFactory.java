package by.avdeev.task08.dao.factory;

import by.avdeev.task08.dao.PhoneDAO;
import by.avdeev.task08.dao.impl.PhoneDAOImpl;

public class DAOFactory {
    private static DAOFactory instance = new DAOFactory();
    private PhoneDAO phoneDAO = new PhoneDAOImpl();

    public static DAOFactory getInstance() {
        return instance;
    }

    public PhoneDAO getPhoneDAO() {
        return phoneDAO;
    }
}
