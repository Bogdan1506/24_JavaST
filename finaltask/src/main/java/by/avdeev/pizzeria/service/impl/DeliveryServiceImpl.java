package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.DeliveryDAOImpl;
import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.entity.OrderPosition;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.transaction.DAOType;

import java.sql.Date;

public class DeliveryServiceImpl extends StandardServiceImpl<Delivery> implements DeliveryService {
    @Override
    public Delivery findByOrderPosition(OrderPosition orderPosition) throws ServiceException {
        AbstractDAO<Delivery> abstractDAO = transaction.createDao(DAOType.DELIVERY);
        DeliveryDAOImpl deliveryDAO = (DeliveryDAOImpl) abstractDAO;
        Delivery delivery;
        try {
            delivery = deliveryDAO.findByOrderPosition(orderPosition);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return delivery;
    }

    @Override
    public int findByDate(Date date) throws ServiceException {
        AbstractDAO<Delivery> abstractDAO = transaction.createDao(DAOType.DELIVERY);
        DeliveryDAOImpl deliveryDAO = (DeliveryDAOImpl) abstractDAO;
        int count;
        try {
            count = deliveryDAO.findByDate(date);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return count;
    }
}
