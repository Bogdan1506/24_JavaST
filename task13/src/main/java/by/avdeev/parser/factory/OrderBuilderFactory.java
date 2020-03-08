package by.avdeev.parser.factory;

import by.avdeev.parser.builder.AbstractOrdersBuilder;
import by.avdeev.parser.builder.sax.OrderSAXBuilder;
import by.avdeev.parser.builder.OrderDomBuilder;
import by.avdeev.parser.builder.OrderStAXBuilder;
import by.avdeev.parser.service.ServiceException;

public class OrderBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    public AbstractOrdersBuilder createOrderBuilder(String typeParser) throws ServiceException {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new OrderDomBuilder();
            case STAX:
                return new OrderStAXBuilder();
            case SAX:
                return new OrderSAXBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
