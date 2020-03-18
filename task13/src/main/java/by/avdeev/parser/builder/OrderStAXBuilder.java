package by.avdeev.parser.builder;

import by.avdeev.parser.entity.Dough;
import by.avdeev.parser.entity.enumclass.DoughName;
import by.avdeev.parser.entity.Goods;
import by.avdeev.parser.entity.OrderPosition;
import by.avdeev.parser.entity.Pizza;
import by.avdeev.parser.entity.enumclass.PizzaName;
import by.avdeev.parser.entity.Size;
import by.avdeev.parser.entity.enumclass.SizeName;
import by.avdeev.parser.entity.User;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import by.avdeev.parser.entity.Order;
import by.avdeev.parser.entity.enumclass.OrderEnum;
import by.avdeev.parser.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderStAXBuilder extends AbstractOrdersBuilder {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameter is {}";
    private final static String RESULT = "return value is {}";
    private XMLInputFactory inputFactory;

    public OrderStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public void buildSetOrders(String fileName) throws ServiceException {
        logger.debug(START);
        logger.debug(PARAM, fileName);
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(fileName))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();

                    if (OrderEnum.valueOf(name.toUpperCase()) == OrderEnum.ORDER) {
                        Order order = buildOrder(reader);
                        orders.add(order);
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            throw new ServiceException(e);
        }
    }

    private Order buildOrder(XMLStreamReader reader) throws XMLStreamException, ServiceException {
        logger.debug(START);
        logger.debug(PARAM, reader);
        Order order = new Order();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (OrderEnum.valueOf(name.toUpperCase())) {
                        case ID:
                            order.setId(Integer.parseInt(getXMLText(reader)));
                            break;
                        case PIZZA:
                            order.setPizza(getXMLPizza(reader));
                            break;
                        case ORDERPOSITION:
                            order.setOrderPosition(getXMLOrderPosition(reader));
                            break;
                        case PRICE:
                            order.setPrice(Double.parseDouble(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (OrderEnum.valueOf(name.toUpperCase()) == OrderEnum.ORDER) {
                        return order;
                    }
                    break;
            }
        }
        logger.debug(RESULT, order);
        return order;
    }

    private OrderPosition getXMLOrderPosition(XMLStreamReader reader) throws XMLStreamException, ServiceException {
        logger.debug(START);
        logger.debug(PARAM, reader);
        OrderPosition orderPosition = new OrderPosition();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (OrderEnum.valueOf(name.toUpperCase())) {
                        case ID:
                            orderPosition.setId(Integer.parseInt(getXMLText(reader)));
                            break;
                        case DATE:
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date date;
                            try {
                                date = simpleDateFormat.parse(getXMLText(reader));
                            } catch (ParseException e) {
                                throw new ServiceException(e);
                            }
                            orderPosition.setDate(date);
                            break;
                        case USER:
                            orderPosition.setUser(getXMLUser(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (OrderEnum.valueOf(name.toUpperCase()) == OrderEnum.ORDERPOSITION) {
                        return orderPosition;
                    }
                    break;
                default:

            }
        }
        logger.debug(RESULT, orderPosition);
        return orderPosition;
    }

    private Pizza getXMLPizza(XMLStreamReader reader) throws XMLStreamException {
        logger.debug(START);
        logger.debug(PARAM, reader);
        Pizza pizza = new Pizza();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (OrderEnum.valueOf(name.toUpperCase())) {
                        case ID:
                            pizza.setId(Integer.parseInt(getXMLText(reader)));
                            break;
                        case GOODS:
                            pizza.setGoods(getXMLGoods(reader));
                            break;
                        case DOUGH:
                            pizza.setDough(getXMLDough(reader));
                            break;
                        case SIZE:
                            pizza.setSize(getXMLSize(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (OrderEnum.valueOf(name.toUpperCase()) == OrderEnum.PIZZA) {
                        return pizza;
                    }
                    break;
            }
        }
        logger.debug(RESULT, pizza);
        return pizza;
    }

    private Goods getXMLGoods(XMLStreamReader reader) throws XMLStreamException {
        logger.debug(START);
        logger.debug(PARAM, reader);
        Goods goods = new Goods();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (OrderEnum.valueOf(name.toUpperCase())) {
                        case ID:
                            goods.setId(Integer.parseInt(getXMLText(reader)));
                            break;
                        case NAME:
                            goods.setName(PizzaName.valueOf(getXMLText(reader)));
                            break;
                        case DESCRIPTION:
                            goods.setDescription(getXMLText(reader));
                            break;
                        case PICTURE:
                            goods.setPicture(getXMLText(reader));
                            break;
                        case PRICE:
                            goods.setPrice(Double.parseDouble(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (OrderEnum.valueOf(name.toUpperCase()) == OrderEnum.GOODS) {
                        return goods;
                    }
                    break;
            }
        }
        logger.debug(RESULT, goods);
        return goods;
    }

    private Dough getXMLDough(XMLStreamReader reader) throws XMLStreamException {
        logger.debug(START);
        logger.debug(PARAM, reader);
        Dough dough = new Dough();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (OrderEnum.valueOf(name.toUpperCase())) {
                        case ID:
                            dough.setId(Integer.parseInt(getXMLText(reader)));
                            break;
                        case NAME:
                            dough.setName(DoughName.valueOf(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (OrderEnum.valueOf(name.toUpperCase()) == OrderEnum.DOUGH) {
                        return dough;
                    }
                    break;
            }
        }
        logger.debug(RESULT, dough);
        return dough;
    }

    private Size getXMLSize(XMLStreamReader reader) throws XMLStreamException {
        Size size = new Size();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (OrderEnum.valueOf(name.toUpperCase())) {
                        case ID:
                            size.setId(Integer.parseInt(getXMLText(reader)));
                            break;
                        case NAME:
                            size.setName(SizeName.valueOf(getXMLText(reader)));
                            break;
                        case COEFFICIENT:
                            size.setCoefficient(Double.parseDouble(getXMLText(reader)));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (OrderEnum.valueOf(name.toUpperCase()) == OrderEnum.SIZE) {
                        return size;
                    }
                    break;
                default:
            }
        }
        logger.debug(RESULT, size);
        return size;
    }

    private User getXMLUser(XMLStreamReader reader) throws XMLStreamException {
        logger.debug(START);
        logger.debug(PARAM, reader);
        User user = new User();
        int type;
        String name;
        user.setRole(Integer.parseInt(reader.getAttributeValue(0)));
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (OrderEnum.valueOf(name.toUpperCase())) {
                        case ID:
                            user.setId(Integer.parseInt(getXMLText(reader)));
                            break;
                        case LOGIN:
                            user.setLogin(getXMLText(reader));
                            break;
                        case PASSWORD:
                            user.setPassword(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (OrderEnum.valueOf(name.toUpperCase()) == OrderEnum.USER) {
                        return user;
                    }
                    break;
                default:
            }
        }
        logger.debug(RESULT, user);
        return user;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        logger.debug(RESULT, text);
        return text;
    }
}

