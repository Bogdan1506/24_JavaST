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
    private final Map<Type, AbstractDAO<? extends Entity>> repository = new ConcurrentHashMap<>();

    private DAOTypeProvider() {
        repository.put(Type.USER, new UserDAOImpl());
        repository.put(Type.PROFILE, new ProfileDAOImpl());
        repository.put(Type.PRODUCT, new ProductDAOImpl());
        repository.put(Type.ITEM, new ItemDAOImpl());
        repository.put(Type.ORDER, new OrderDAOImpl());
        repository.put(Type.ORDER_POSITION, new OrderPositionDAOImpl());
        repository.put(Type.DELIVERY, new DeliveryDAOImpl());
    }

    public AbstractDAO<? extends Entity> findDAO(Type type) {
        return repository.get(type);
    }

    public static DAOTypeProvider getDaoTypeProvider() {
        if (daoTypeProvider == null) {
            daoTypeProvider = new DAOTypeProvider();
        }
        return daoTypeProvider;
    }
}
