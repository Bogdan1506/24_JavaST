package by.avdeev.pizzeria.transaction;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.impl.ItemDAOImpl;
import by.avdeev.pizzeria.dao.impl.ProductDAOImpl;
import by.avdeev.pizzeria.dao.impl.ProfileDAOImpl;
import by.avdeev.pizzeria.dao.impl.UserDAOImpl;
import by.avdeev.pizzeria.entity.Entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DAOTypeProvider {
    private static DAOTypeProvider daoTypeProvider = new DAOTypeProvider();
    private final Map<DAOType, AbstractDAO<? extends Entity>> repository = new ConcurrentHashMap<>();

    private DAOTypeProvider() {
        repository.put(DAOType.USER, new UserDAOImpl());
        repository.put(DAOType.PROFILE, new ProfileDAOImpl());
        repository.put(DAOType.PRODUCT, new ProductDAOImpl());
        repository.put(DAOType.ITEM, new ItemDAOImpl());
    }

    public AbstractDAO<? extends Entity> findDAO(DAOType daoType) {
        return repository.get(daoType);
    }

    public static DAOTypeProvider getDaoTypeProvider() {
        return daoTypeProvider;
    }
}
