package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.service.ItemService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.TransactionService;
import by.avdeev.pizzeria.transaction.DAOType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ItemServiceImpl extends TransactionService implements ItemService {
    private static final DAOType DAO_TYPE = DAOType.ITEM;
    private static Logger logger = LogManager.getLogger();

    @Override
    public int create(Item item) throws ServiceException {
        AbstractDAO<Item> itemDAO = transaction.createDao(DAO_TYPE);
        logger.debug("itemDAO={}", itemDAO);
        int lastId;
        try {
            itemDAO.create(item);
            lastId = itemDAO.findLastInsertId();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return lastId;
    }

    @Override
    public List<Item> findAll() throws ServiceException {
        AbstractDAO<Item> itemDAO = transaction.createDao(DAO_TYPE);
        List<Item> items;
        try {
            items = itemDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return items;
    }

    @Override
    public Item findById(int id) throws ServiceException {
        AbstractDAO<Item> itemDAO = transaction.createDao(DAO_TYPE);
        Item item;
        try {
            item = itemDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return item;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        AbstractDAO<Item> itemDAO = transaction.createDao(DAO_TYPE);
        boolean isDeleted;
        try {
            isDeleted = itemDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public void update(Item item) throws ServiceException {
        AbstractDAO<Item> itemDAO = transaction.createDao(DAO_TYPE);
        try {
            itemDAO.update(item);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
