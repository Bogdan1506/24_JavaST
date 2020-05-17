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
import by.avdeev.pizzeria.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
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

    @Override
    public int create(Map<String, Object> parameters, Map<String, String> invalidParameters) throws ServiceException {
        AbstractDAO<Product> abstractDAO = transaction.createDao(type);
        ProductDAOImpl dao = (ProductDAOImpl) abstractDAO;
        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
        Validator validator = validatorFactory.findValidator(type);
        boolean isParamValid = validator.validate(parameters, invalidParameters);
        if (isParamValid) {
            Creator<Product> creator = new ProductCreator();
            Product product = creator.create(parameters);
            logger.debug("product={}", product);
            Product existProduct = findByName(product.getName());
            logger.debug("existProduct={}", existProduct);
            if (existProduct == null) {
                InputStream picture = (InputStream) parameters.get("picture");
                try {
                    if (picture != null) {
                        dao.create(product, picture);
                    } else {
                        dao.create(product);
                    }
                    return dao.findLastInsertId();
                } catch (DAOException e) {
                    throw new ServiceException(e);
                }
            } else {
                invalidParameters.put("name", "Such name exists!");
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
    public int update(Map<String, Object> parameters, Map<String, String> invalidParameters) throws ServiceException {
        AbstractDAO<Product> abstractDAO = transaction.createDao(type);
        ProductDAOImpl dao = (ProductDAOImpl) abstractDAO;
        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
        Validator validator = validatorFactory.findValidator(type);
        boolean isParamValid = validator.validate(parameters, invalidParameters);
        if (isParamValid) {
            Creator<Product> creator = new ProductCreator();
            Product product = creator.create(parameters);
            logger.debug("product={}", product);
            int id = Integer.parseInt((String) parameters.get("id"));
            Product existProduct = findById(id);
            logger.debug("existProduct={}", existProduct);
            if (existProduct != null) {
                InputStream picture = (InputStream) parameters.get("picture");
                product.setId(id);
                try {
                    if (picture != null) {
                        dao.update(product, picture);
                    } else {
                        dao.update(product);
                    }
                    return id;
                } catch (DAOException e) {
                    throw new ServiceException(e);
                }
            } else {
                invalidParameters.put("id", "Such product doesn't exist!");
            }
        }
        return -1;
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
