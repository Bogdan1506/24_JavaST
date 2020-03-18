package by.avdeev.parser.factory;

import by.avdeev.parser.builder.AbstractOrdersBuilder;
import by.avdeev.parser.builder.sax.OrderSAXBuilder;
import by.avdeev.parser.builder.OrderDomBuilder;
import by.avdeev.parser.builder.OrderStAXBuilder;
import by.avdeev.parser.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderBuilderFactory {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameter is {}";
    private final static String RESULT = "return value is {}";

    private enum TypeParser {
        SAX, STAX, DOM
    }

    public AbstractOrdersBuilder createOrderBuilder(String typeParser) throws ServiceException {
        logger.debug(START);
        logger.debug(PARAM, typeParser);
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                OrderDomBuilder orderDomBuilder = new OrderDomBuilder();
                logger.debug(RESULT, orderDomBuilder);
                return orderDomBuilder;
            case STAX:
                OrderStAXBuilder orderStAXBuilder = new OrderStAXBuilder();
                logger.debug(RESULT, orderStAXBuilder);
                return orderStAXBuilder;
            case SAX:
                OrderSAXBuilder orderSAXBuilder = new OrderSAXBuilder();
                logger.debug(RESULT, orderSAXBuilder);
                return orderSAXBuilder;
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
