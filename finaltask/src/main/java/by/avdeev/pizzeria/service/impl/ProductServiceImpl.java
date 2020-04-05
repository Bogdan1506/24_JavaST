package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.TransactionService;
import by.avdeev.pizzeria.transaction.DAOType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductServiceImpl extends TransactionService implements ProductService {
    private static final DAOType DAO_TYPE = DAOType.PRODUCT;
    private static Logger logger = LogManager.getLogger();

    @Override
    public int create(Product product) throws ServiceException {
        AbstractDAO<Product> productDAO = transaction.createDao(DAO_TYPE);
        int lastId;
        try {
            productDAO.create(product);
            lastId = productDAO.findLastInsertId();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return lastId;
    }

    @Override
    public List<Product> findAll() throws ServiceException {
        AbstractDAO<Product> productDAO = transaction.createDao(DAO_TYPE);
        List<Product> products;
        try {
            products = productDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return products;
    }

    @Override
    public Product findById(int id) throws ServiceException {
        AbstractDAO<Product> productDAO = transaction.createDao(DAO_TYPE);
        Product product;
        try {
            product = productDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return product;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        AbstractDAO<Product> productDAO = transaction.createDao(DAO_TYPE);
        boolean isDeleted;
        try {
            isDeleted = productDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public void update(Product product) throws ServiceException {
        AbstractDAO<Product> productDAO = transaction.createDao(DAO_TYPE);
        try {
            productDAO.update(product);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
