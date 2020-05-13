package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.service.ItemService;
import by.avdeev.pizzeria.service.ServiceException;

import java.util.List;

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
}

