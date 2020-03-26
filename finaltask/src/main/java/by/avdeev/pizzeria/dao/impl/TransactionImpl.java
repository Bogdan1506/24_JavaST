package by.avdeev.pizzeria.dao.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.entity.Entity;
import by.avdeev.pizzeria.entity.Type;
import by.avdeev.pizzeria.dao.Transaction;

import java.sql.Connection;
import java.util.EnumMap;

public class TransactionImpl implements Transaction {
    private EnumMap<Type, AbstractDAO<? extends Entity>> repository = new EnumMap<>(Type.class);
    private Connection connection;


    public TransactionImpl(Connection connection) {
        this.connection = connection;

        repository.put(Type.USER, new UserDAOImpl());  //todo remove
    }

    @Override
    public <T extends AbstractDAO<? extends Entity>> T createDAO(Type type) {
        @SuppressWarnings("unchecked")
        T dao = (T) repository.get(type);
        dao.setConnection(connection);
        return dao;
    }
}
