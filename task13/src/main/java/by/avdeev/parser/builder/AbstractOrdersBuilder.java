package by.avdeev.parser.builder;

import by.avdeev.parser.entity.Order;
import by.avdeev.parser.service.ServiceException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractOrdersBuilder {
    protected Set<Order> orders;

    public AbstractOrdersBuilder() {
        orders = new HashSet<>();
    }

    public Set<Order> getOrders() {
        return orders;
    }

    abstract public void buildSetOrders(String fileName) throws ServiceException;
}
