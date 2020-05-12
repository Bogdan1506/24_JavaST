package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.OrderPositionDAOImpl;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.Order;
import by.avdeev.pizzeria.entity.OrderPosition;
import by.avdeev.pizzeria.service.OrderPositionService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.transaction.Type;

import java.util.List;

public class OrderPositionServiceImpl extends StandardServiceImpl<OrderPosition> implements OrderPositionService {

    @Override
    public OrderPosition findByItem(Item item) throws ServiceException {
        AbstractDAO<OrderPosition> abstractDAO = transaction.createDao(Type.ORDER_POSITION);
        OrderPositionDAOImpl orderPositionDAO = (OrderPositionDAOImpl) abstractDAO;
        OrderPosition orderPosition;
        try {
            orderPosition = orderPositionDAO.findByItem(item);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orderPosition;
    }

    @Override
    public List<OrderPosition> findByOrder(Order order) throws ServiceException {
        AbstractDAO<OrderPosition> abstractDAO = transaction.createDao(Type.ORDER_POSITION);
        OrderPositionDAOImpl orderPositionDAO = (OrderPositionDAOImpl) abstractDAO;
        List<OrderPosition> orderPositions;
        try {
            orderPositions = orderPositionDAO.findByOrderPosition(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orderPositions;
    }
}
