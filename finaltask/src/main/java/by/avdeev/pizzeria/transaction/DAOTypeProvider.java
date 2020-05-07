package by.avdeev.pizzeria.transaction;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.impl.DeliveryDAOImpl;
import by.avdeev.pizzeria.dao.impl.ItemDAOImpl;
import by.avdeev.pizzeria.dao.impl.OrderDAOImpl;
import by.avdeev.pizzeria.dao.impl.OrderPositionDAOImpl;
import by.avdeev.pizzeria.dao.impl.ProductDAOImpl;
import by.avdeev.pizzeria.dao.impl.ProfileDAOImpl;
import by.avdeev.pizzeria.dao.impl.UserDAOImpl;
import by.avdeev.pizzeria.entity.Entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DAOTypeProvider {
    private static DAOTypeProvider daoTypeProvider;
    private final Map<DAOType, AbstractDAO<? extends Entity>> repository = new ConcurrentHashMap<>();

    private DAOTypeProvider() {
        repository.put(DAOType.USER, new UserDAOImpl());
        repository.put(DAOType.PROFILE, new ProfileDAOImpl());
        repository.put(DAOType.PRODUCT, new ProductDAOImpl());
        repository.put(DAOType.ITEM, new ItemDAOImpl());
        repository.put(DAOType.ORDER, new OrderDAOImpl());
        repository.put(DAOType.ORDER_POSITION, new OrderPositionDAOImpl());
        repository.put(DAOType.DELIVERY, new DeliveryDAOImpl());
    }

    public AbstractDAO<? extends Entity> findDAO(DAOType daoType) {
        return repository.get(daoType);
    }

    public static DAOTypeProvider getDaoTypeProvider() {
        if (daoTypeProvider == null) {
            daoTypeProvider = new DAOTypeProvider();
        }
        return daoTypeProvider;
    }
}
