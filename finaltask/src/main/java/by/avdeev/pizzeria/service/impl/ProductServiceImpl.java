package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.ProductDAOImpl;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.creator.Creator;
import by.avdeev.pizzeria.service.creator.ProductCreator;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.impl.ProductValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class ProductServiceImpl extends StandardServiceImpl<Product> implements ProductService {
    private static Logger logger = LogManager.getLogger();

    @Override
    public int create(Product product) throws ServiceException {
        Product checkProduct = findByName(product.getName());
        if (checkProduct == null) {
            return super.create(product);
        }
        return -1;
    }

    @Override
    public List<Product> findByType(Product.Type type) throws ServiceException {
        AbstractDAO<Product> abstractDAO = transaction.createDao(this.type);
        ProductDAOImpl productDAO = (ProductDAOImpl) abstractDAO;
        List<Product> products;
        try {
            products = productDAO.findByType(type);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return products;
    }

    public int create(Map<String, Object> parameters, Map<String, String> invalidParameters) throws ServiceException {
        Validator validator = new ProductValidator();
        boolean isParamValid = validator.validate(parameters, invalidParameters);
        if (isParamValid) {
            Creator<Product> creator = new ProductCreator();
            Product product = creator.create(parameters);
            logger.debug("product={}", product);
            Product existProduct = findByName(product.getName());
            logger.debug("existProduct={}", existProduct);
            if (existProduct == null) {
                return create(product);
            }
        }
        return -1;
    }

    @Override
    public Product findByName(String name) throws ServiceException {
        AbstractDAO<Product> abstractDAO = transaction.createDao(type);
        ProductDAOImpl productDAO = (ProductDAOImpl) abstractDAO;
        Product product;
        try {
            product = productDAO.findByName(name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return product;
    }

    @Override
    public Map<String, Integer> findCount() throws ServiceException {
        AbstractDAO<Product> abstractDAO = transaction.createDao(type);
        ProductDAOImpl productDAO = (ProductDAOImpl) abstractDAO;
        Map<String, Integer> products;
        try {
            products = productDAO.findCountProduct();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return products;
    }
}
