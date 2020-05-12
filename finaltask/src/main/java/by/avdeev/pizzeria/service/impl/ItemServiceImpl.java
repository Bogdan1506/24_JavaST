package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.service.ItemService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.creator.Creator;
import by.avdeev.pizzeria.service.creator.ItemCreator;

import java.util.List;
import java.util.Map;

public class ItemServiceImpl extends StandardServiceImpl<Item> implements ItemService {
    @Override
    public Item create(Map<String, Object> parameters) throws ServiceException {
        Creator<Item> creator = new ItemCreator();
        return creator.create(parameters);
    }

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
            super.create(item);
        }
        item.setId(itemId);
        return itemId;
    }
}

