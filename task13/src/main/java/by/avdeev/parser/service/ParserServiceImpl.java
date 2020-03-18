package by.avdeev.parser.service;

import by.avdeev.parser.builder.AbstractOrdersBuilder;
import by.avdeev.parser.entity.Order;
import by.avdeev.parser.factory.OrderBuilderFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class ParserServiceImpl implements ParserService {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameters are {}, {}";
    private final static String RESULT = "return value is {}";

    @Override
    public Set<Order> parse(String pathname, String typeParser) throws ServiceException {
        logger.debug(START);
        logger.debug(PARAM, pathname, typeParser);
        OrderBuilderFactory factory = new OrderBuilderFactory();
        ValidatorSAXXSD validator = new ValidatorSAXXSD();
        validator.validate(pathname);
        AbstractOrdersBuilder builder = factory.createOrderBuilder(typeParser);
        builder.buildSetOrders(pathname);
        Set<Order> orders = builder.getOrders();
        logger.debug(RESULT, orders);
        return orders;
    }
}
