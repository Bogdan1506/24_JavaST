package by.avdeev.parser.builder.sax;

import by.avdeev.parser.builder.AbstractOrdersBuilder;
import by.avdeev.parser.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class OrderSAXBuilder extends AbstractOrdersBuilder {
    private OrderHandler sh;
    private XMLReader reader;
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameter is {}";

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
        logger.debug(START);
        logger.debug(PARAM, fileName);
        try {
            reader.parse(fileName);
        } catch (SAXException | IOException e) {
            throw new ServiceException(e);
        }
        orders = sh.getOrders();
    }
}
