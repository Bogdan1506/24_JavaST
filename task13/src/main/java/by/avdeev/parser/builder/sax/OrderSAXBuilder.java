package by.avdeev.parser.builder.sax;

import by.avdeev.parser.builder.AbstractOrdersBuilder;
import by.avdeev.parser.service.ServiceException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class OrderSAXBuilder extends AbstractOrdersBuilder {
    private OrderHandler sh;
    private XMLReader reader;

    public OrderSAXBuilder() throws ServiceException {
        sh = new OrderHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(sh);
        } catch (SAXException e) {
            throw new ServiceException(e);
        }
    }

    public void buildSetOrders(String fileName) throws ServiceException {
        try {
            reader.parse(fileName);
        } catch (SAXException | IOException e) {
            throw new ServiceException(e);
        }
        orders = sh.getOrders();
    }
}
