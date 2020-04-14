package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.ProductDAOImpl;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductServiceImpl extends StandardServiceImpl<Product> implements ProductService {
    private static Logger logger = LogManager.getLogger();

    @Override
    public List<Product> findByType(Product.Type type) throws ServiceException {
        AbstractDAO<Product> abstractDAO = transaction.createDao(daoType);
        ProductDAOImpl productDAO = (ProductDAOImpl) abstractDAO;
        List<Product> products;
        try {
            products = productDAO.findByType(type);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return products;
    }
}
