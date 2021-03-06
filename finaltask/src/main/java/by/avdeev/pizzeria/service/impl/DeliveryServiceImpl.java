package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.DeliveryDAOImpl;
import by.avdeev.pizzeria.entity.Delivery;
import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.ValidatorFactory;

import java.util.Date;
import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.DATE;
import static by.avdeev.pizzeria.command.ConstantRepository.PAYMENT;

public class DeliveryServiceImpl extends StandardServiceImpl<Delivery>
        implements DeliveryService {

    /**
     * @param parameters        Gotten inputs from user.
     * @param invalidParameters List of incorrect inputs from user.
     * @param id                ${@link Delivery} bean id.
     * @return true if the bean was updated.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    @Override
    public boolean update(final Map<String, Object> parameters,
                          final Map<String, String> invalidParameters,
                          final int id) throws ServiceException {
        AbstractDAO<Delivery> abstractDAO = getTransaction().createDao(getType());
        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
        Validator validator = validatorFactory.findValidator(getType());
        if (validator.validate(parameters, invalidParameters)) {
            Delivery delivery = new Delivery();
            Date date = (Date) parameters.get(DATE);
            delivery.setDate(date);
            delivery.setPayment((Delivery.Payment) parameters.get(PAYMENT));
            delivery.setId(id);
            try {
                return abstractDAO.update(delivery);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    /**
     * Counts all deliveries on determined dates.
     *
     * @param firstDate  Start of searching.
     * @param secondDate End of searching.
     * @return Count of found dates.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    @Override
    public int findCountByDate(java.sql.Date firstDate, java.sql.Date secondDate)
            throws ServiceException {
        AbstractDAO<Delivery> abstractDAO = getTransaction().createDao(getType());
        DeliveryDAOImpl deliveryDAO = (DeliveryDAOImpl) abstractDAO;
        int count;
        try {
            count = deliveryDAO.findCountByDate(firstDate, secondDate);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return count;
    }
}
