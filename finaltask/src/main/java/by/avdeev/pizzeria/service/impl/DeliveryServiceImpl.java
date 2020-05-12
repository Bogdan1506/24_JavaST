package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.DeliveryDAOImpl;
import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.entity.OrderPosition;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.impl.DeliveryValidator;
import by.avdeev.pizzeria.transaction.Type;

import java.sql.Date;
import java.util.Map;

public class DeliveryServiceImpl extends StandardServiceImpl<Delivery> implements DeliveryService {

    @Override
    public Delivery findByOrderPosition(OrderPosition orderPosition) throws ServiceException {
        AbstractDAO<Delivery> abstractDAO = transaction.createDao(Type.DELIVERY);
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
        AbstractDAO<Delivery> abstractDAO = transaction.createDao(Type.DELIVERY);
        DeliveryDAOImpl deliveryDAO = (DeliveryDAOImpl) abstractDAO;
        int count;
        try {
            count = deliveryDAO.findByDate(date);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return count;
    }

    @Override
    public int create(Map<String, Object> parameters, Map<String, String> invalidParameters, Delivery delivery) throws ServiceException {
        Validator validator = new DeliveryValidator();
        if (validator.validate(parameters, invalidParameters)) {
            delivery.setDate((Date) parameters.get("date"));
            return create(delivery);
        } else {
            return -1;
        }
    }
}
