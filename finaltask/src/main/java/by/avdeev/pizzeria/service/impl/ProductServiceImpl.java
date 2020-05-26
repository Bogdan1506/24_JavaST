package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.ProductDAOImpl;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.creator.Creator;
import by.avdeev.pizzeria.service.creator.CreatorFactory;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.NAME;

public class ProductServiceImpl extends StandardServiceImpl<Product>
        implements ProductService {
    /**
     * log4j2 is used for logging.
     */
    private static Logger logger = LogManager.getLogger(ProductServiceImpl.class);

    /**
     * Finds all product beans of the type.
     *
     * @param type ${@link by.avdeev.pizzeria.entity.Product.Type} of product bean.
     * @return List of product beans.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    @Override
    public List<Product> findByType(final Product.Type type)
            throws ServiceException {
        AbstractDAO<Product> abstractDAO = getTransaction().createDao(getType());
        ProductDAOImpl productDAO = (ProductDAOImpl) abstractDAO;
        List<Product> products;
        try {
            products = productDAO.findByType(type);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return products;
    }

    /**
     * Creates product bean.
     *
     * @param parameters        Gotten inputs from user.
     * @param invalidParameters List of incorrect inputs from user.
     * @return Id of the pushed product bean.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    @Override
    public int create(final Map<String, Object> parameters,
                      final Map<String, String> invalidParameters)
            throws ServiceException {
        AbstractDAO<Product> abstractDAO = getTransaction().createDao(getType());
        ProductDAOImpl dao = (ProductDAOImpl) abstractDAO;
        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
        Validator validator = validatorFactory.findValidator(getType());
        boolean isParamValid = validator.validate(parameters, invalidParameters);
        if (isParamValid) {
            CreatorFactory creatorFactory = CreatorFactory.getInstance();
            @SuppressWarnings("unchecked")
            Creator<Product> creator = creatorFactory.findCreator(getType());
            Product product = creator.create(parameters);
            logger.debug("product={}", product);
            Product existProduct = findByName(product.getName());
            logger.debug("existProduct={}", existProduct);
            if (existProduct == null) {
                try {
                    return dao.create(product);
                } catch (DAOException e) {
                    throw new ServiceException(e);
                }
            } else {
                invalidParameters.put(NAME, "Such name exists!");
            }
        }
        return -1;
    }

    /**
     * Finds product bean by its name.
     *
     * @param name Bean ${@link Product}.
     * @return Bean ${@link Product}.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    private Product findByName(final String name) throws ServiceException {
        AbstractDAO<Product> abstractDAO = getTransaction().createDao(getType());
        ProductDAOImpl productDAO = (ProductDAOImpl) abstractDAO;
        Product product;
        try {
            product = productDAO.findByName(name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return product;
    }

    /**
     * Updates product bean.
     *
     * @param parameters        Gotten inputs from user.
     * @param invalidParameters List of incorrect inputs from user.
     * @param id                Id of ${@link Product} bean.
     * @return Id of the pushed product bean.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    @Override
    public int update(final Map<String, Object> parameters,
                      final Map<String, String> invalidParameters, final int id)
            throws ServiceException {
        AbstractDAO<Product> abstractDAO = getTransaction().createDao(getType());
        ProductDAOImpl dao = (ProductDAOImpl) abstractDAO;
        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
        Validator validator = validatorFactory.findValidator(getType());
        boolean isParamValid = validator.validate(parameters, invalidParameters);
        if (isParamValid) {
            CreatorFactory creatorFactory = CreatorFactory.getInstance();
            @SuppressWarnings("unchecked")
            Creator<Product> creator = creatorFactory.findCreator(getType());
            Product product = creator.create(parameters);
            logger.debug("product={}", product);
            Product existProduct = findById(id);
            logger.debug("existProduct={}", existProduct);
            if (existProduct != null) {
                product.setId(id);
                try {
                    dao.update(product);
                    return id;
                } catch (DAOException e) {
                    throw new ServiceException(e);
                }
            } else {
                invalidParameters.put(ID, "Such product doesn't exist!");
            }
        }
        return -1;
    }

    /**
     * Counts total number of orders for each once ordered product position.
     *
     * @return Map with ${@link Product} name and its count of ${@link by.avdeev.pizzeria.entity.Order}.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    @Override
    public Map<String, Integer> findCount() throws ServiceException {
        AbstractDAO<Product> abstractDAO = getTransaction().createDao(getType());
        ProductDAOImpl productDAO = (ProductDAOImpl) abstractDAO;
        Map<String, Integer> products;
        try {
            products = productDAO.findCountProduct();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return products;
    }

    /**
     * Deletes the product.
     *
     * @param product Bean ${@link Product}.
     * @return True if it was deleted else false.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    @Override
    public boolean delete(Product product) throws ServiceException {
        AbstractDAO<Product> abstractDAO = getTransaction().createDao(getType());
        try {
            return abstractDAO.delete(product);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
