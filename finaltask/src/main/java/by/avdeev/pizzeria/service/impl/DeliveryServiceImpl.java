package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.DeliveryDAOImpl;
import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.entity.OrderPosition;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.ValidatorFactory;
import by.avdeev.pizzeria.transaction.Type;

import java.util.Date;
import java.util.Map;

public class DeliveryServiceImpl extends StandardServiceImpl<Delivery> implements DeliveryService {

    @Override
    public Delivery findByOrderPosition(OrderPosition orderPosition) throws ServiceException {
        AbstractDAO<Delivery> abstractDAO = transaction.createDao(type);
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
    public boolean update(Map<String, Object> parameters, Map<String, String> invalidParameters, int id) throws ServiceException {
        AbstractDAO<Delivery> abstractDAO = transaction.createDao(type);
        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
        Validator validator = validatorFactory.findValidator(type);
        if (validator.validate(parameters, invalidParameters)) {
            Delivery delivery = new Delivery();
            delivery.setDate((Date) parameters.get("date"));
            delivery.setPayment((Delivery.Payment) parameters.get("payment"));
            try {
                return abstractDAO.update(delivery);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }
}
