package by.avdeev.pizzeria.transaction;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.entity.Entity;

public interface Transaction {
    /**
     * Creates specified dao by ${@link Type}.
     *
     * @param type ${@link Type}.
     * @param <T>  bean ${@link Entity}.
     * @return bean ${@link AbstractDAO}.
     */
    <T extends Entity> AbstractDAO<T> createDao(Type type);
}
