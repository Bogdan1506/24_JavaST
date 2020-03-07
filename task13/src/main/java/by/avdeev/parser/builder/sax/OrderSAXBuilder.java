package by.avdeev.parser.builder.sax;

import by.avdeev.parser.builder.AbstractOrdersBuilder;
import by.avdeev.parser.entity.Order;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class OrderSAXBuilder extends AbstractOrdersBuilder {
    private Set<Order> orders;
    private OrderHandler sh;
    private XMLReader reader;

    public OrderSAXBuilder() {
        sh = new OrderHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(sh);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        }
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void buildSetOrders(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            System.err.print("ошибка I/О потока: " + e);
        }
        orders = sh.getOrders();
    }
}
