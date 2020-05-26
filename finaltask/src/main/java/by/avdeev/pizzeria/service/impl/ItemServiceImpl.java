package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ItemService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.creator.Creator;
import by.avdeev.pizzeria.service.creator.CreatorFactory;
import by.avdeev.pizzeria.transaction.Type;

import java.util.List;
import java.util.Map;

public class ItemServiceImpl extends StandardServiceImpl<Item>
        implements ItemService {
    /**
     * @param item Bean ${@link Item}.
     * @return Id of the pushed ${@link Item} bean.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    @Override
    public int create(final Item item) throws ServiceException {
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

    /**
     * @param parameters List of user inputs.
     * @param cart       Connected with session list.
     * @throws ServiceException if there was an exception in DAO layer.
     */
    @Override
    public void create(final Map<String, Object> parameters,
                       final List<Item> cart) throws ServiceException {
        if(parameters == null) {
            return;
        }
        CreatorFactory creatorFactory = CreatorFactory.getInstance();
        @SuppressWarnings("unchecked")
        Creator<Item> creator = creatorFactory.findCreator(getType());
        Item item = creator.create(parameters);
        AbstractDAO<Product> dao = getTransaction().createDao(Type.PRODUCT);
        Product product;
        try {
            product = dao.findById(item.getProduct().getId());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        item.setProduct(product);
        item.setId(create(item));
        cart.add(item);
    }
}

