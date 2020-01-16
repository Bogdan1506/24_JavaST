package by.avdeev.task10final.gift.dao.factory;

import by.avdeev.task10final.gift.dao.GiftDAO;
import by.avdeev.task10final.gift.dao.GiftDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory = new DAOFactory();

    private final GiftDAO giftDAO = new GiftDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory;
    }

    public GiftDAO getGiftDAO() {
        return giftDAO;
    }
}
