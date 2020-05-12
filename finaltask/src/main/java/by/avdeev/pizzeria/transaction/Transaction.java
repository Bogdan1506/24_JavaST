package by.avdeev.pizzeria.transaction;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.entity.Entity;

public interface Transaction {
    <T extends Entity> AbstractDAO<T> createDao(Type type);
}