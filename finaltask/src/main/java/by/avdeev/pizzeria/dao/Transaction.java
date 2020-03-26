package by.avdeev.pizzeria.dao;

import by.avdeev.pizzeria.entity.Entity;
import by.avdeev.pizzeria.entity.Type;

public interface Transaction {
    <T extends AbstractDAO<? extends Entity>> T createDAO(Type type);
}
