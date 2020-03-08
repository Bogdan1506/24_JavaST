package by.avdeev.parser.service;

import by.avdeev.parser.builder.AbstractOrdersBuilder;
import by.avdeev.parser.entity.Order;
import by.avdeev.parser.factory.OrderBuilderFactory;

import java.util.Set;

public class ParserServiceImpl implements ParserService {
    @Override
    public Set<Order> parse(String pathname, String typeParser) throws ServiceException {
        OrderBuilderFactory factory = new OrderBuilderFactory();
        AbstractOrdersBuilder builder = factory.createOrderBuilder(typeParser);
        builder.buildSetOrders(pathname);
        return builder.getOrders();
    }
}
