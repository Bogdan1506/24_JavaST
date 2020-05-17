package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService extends Service {
    int create(Product product) throws ServiceException;

    int create(Map<String, Object> parameters, Map<String, String> invalidParameters) throws ServiceException;

    List<Product> findAll() throws ServiceException;

    Product findById(int id) throws ServiceException;

    Product findByName(String name) throws ServiceException;

    boolean delete(int id) throws ServiceException;

    void update(Product product) throws ServiceException;

    int update(Map<String, Object> parameters, Map<String, String> invalidParameters) throws ServiceException;

    List<Product> findByType(Product.Type type) throws ServiceException;

    Map<String, Integer> findCount() throws ServiceException;
}
