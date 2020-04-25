package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.DeliveryDAOImpl;
import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.entity.OrderPosition;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.transaction.DAOType;

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
}
