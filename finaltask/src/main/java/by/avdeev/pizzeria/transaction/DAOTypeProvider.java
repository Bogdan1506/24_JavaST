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

/**
 * Provides implementations of ${@link AbstractDAO} using singleton.
 */
public final class DAOTypeProvider {
    /**
     * Class instance.
     */
    private static DAOTypeProvider daoTypeProvider;
    /**
     * Consists of all implementations of ${@link AbstractDAO}.
     */
    private final Map<Type, AbstractDAO<? extends Entity>> repository = new ConcurrentHashMap<>();

    /**
     * Private constructor putting data in ${@link #repository}.
     */
    private DAOTypeProvider() {
        repository.put(Type.USER, new UserDAOImpl());
        repository.put(Type.PROFILE, new ProfileDAOImpl());
        repository.put(Type.PRODUCT, new ProductDAOImpl());
        repository.put(Type.ITEM, new ItemDAOImpl());
        repository.put(Type.ORDER, new OrderDAOImpl());
        repository.put(Type.ORDER_POSITION, new OrderPositionDAOImpl());
        repository.put(Type.DELIVERY, new DeliveryDAOImpl());
    }

    /**
     * Finds needed dao.
     *
     * @param type ${@link Type}.
     * @return Specified ${@link AbstractDAO}.
     */
    public AbstractDAO<? extends Entity> findDAO(final Type type) {
        return repository.get(type);
    }

    /**
     * Getting class instance.
     *
     * @return Instance ${@link DAOTypeProvider}
     */
    public static DAOTypeProvider getDaoTypeProvider() {
        if (daoTypeProvider == null) {
            daoTypeProvider = new DAOTypeProvider();
        }
        return daoTypeProvider;
    }
}
