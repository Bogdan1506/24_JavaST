package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.OrderPositionDAOImpl;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.OrderPosition;
import by.avdeev.pizzeria.service.OrderPositionService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.transaction.DAOType;

public class OrderPositionServiceImpl extends StandardServiceImpl<OrderPosition> implements OrderPositionService {
    @Override
    public OrderPosition findByItem(Item item) throws ServiceException {
        AbstractDAO<OrderPosition> abstractDAO = transaction.createDao(DAOType.ORDER_POSITION);
        OrderPositionDAOImpl orderPositionDAO = (OrderPositionDAOImpl) abstractDAO;
        OrderPosition orderPosition;
        try {
            orderPosition = orderPositionDAO.findByItem(item);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orderPosition;
    }
}
