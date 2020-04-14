package by.avdeev.pizzeria.transaction;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.entity.Entity;

import java.sql.Connection;

public class TransactionImpl implements Transaction {
    private Connection connection;

    public TransactionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <T extends Entity> AbstractDAO<T> createDao(DAOType daoType) {
        DAOTypeProvider daoTypeProvider = DAOTypeProvider.getDaoTypeProvider();
        @SuppressWarnings("unchecked")
        AbstractDAO<T> dao = (AbstractDAO<T>) daoTypeProvider.findDAO(daoType);
        dao.setConnection(connection);
        return dao;
    }

}
