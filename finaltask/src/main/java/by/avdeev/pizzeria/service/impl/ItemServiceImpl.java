package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ItemService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.creator.Creator;
import by.avdeev.pizzeria.service.creator.ItemCreator;
import by.avdeev.pizzeria.transaction.Type;

import java.util.List;
import java.util.Map;

public class ItemServiceImpl extends StandardServiceImpl<Item> implements ItemService {

    @Override
    public int create(Item item) throws ServiceException {
        int itemId = 0;
        List<Item> items = findAll();
        if (items.contains(item)) {
            for (Item tempItem : items) {
                if (item.equals(tempItem)) {
                    itemId = tempItem.getId();
                }
            }
        } else {
            itemId = super.create(item);
        }
        item.setId(itemId);
        return itemId;
    }

    @Override
    public void create(Map<String, Object> parameters, List<Item> cart) throws ServiceException {
        Creator<Item> creator = new ItemCreator();
        Item item = creator.create(parameters);
        System.out.println("v service = " + item);
        AbstractDAO<Product> productAbstractDAO = transaction.createDao(Type.PRODUCT);
        Product product;
        try {
            product = productAbstractDAO.findById(item.getProduct().getId());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        item.setProduct(product);
        item.setId(create(item));
        cart.add(item);
    }
}

