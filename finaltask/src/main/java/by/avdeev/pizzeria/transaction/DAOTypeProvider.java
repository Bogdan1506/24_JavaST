package by.avdeev.pizzeria.transaction;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.impl.ProfileDAOImpl;
import by.avdeev.pizzeria.dao.impl.UserDAOImpl;
import by.avdeev.pizzeria.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class DAOTypeProvider {
    private static DAOTypeProvider daoTypeProvider = new DAOTypeProvider();
    private final Map<DAOType, AbstractDAO<? extends Entity>> repository = new HashMap<>();

    private DAOTypeProvider() {
        repository.put(DAOType.USER, new UserDAOImpl());
        repository.put(DAOType.PROFILE, new ProfileDAOImpl());
    }

    public AbstractDAO<? extends Entity> findDAO(DAOType daoType) {
        return repository.get(daoType);
    }

    public static DAOTypeProvider getDaoTypeProvider() {
        return daoTypeProvider;
    }
}
