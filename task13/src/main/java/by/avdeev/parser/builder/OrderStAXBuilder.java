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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import by.avdeev.parser.entity.Order;
import by.avdeev.parser.entity.enumclass.OrderEnum;

public class OrderStAXBuilder extends AbstractOrdersBuilder {
    private HashSet<Order> orders = new HashSet<>();
    private XMLInputFactory inputFactory;

    public OrderStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public XMLInputFactory getInputFactory() {
        return inputFactory;
    }

    public void buildSetOrders(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
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
        } catch (XMLStreamException ex) {
            System.err.println("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private Order buildOrder(XMLStreamReader reader) throws XMLStreamException {
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
        return order;
    }

    private OrderPosition getXMLOrderPosition(XMLStreamReader reader) throws XMLStreamException {
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
                            Date date = null;
                            try {
                                date = simpleDateFormat.parse(getXMLText(reader));
                            } catch (ParseException e) {
                                e.printStackTrace();
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
        return orderPosition;
    }

    private Pizza getXMLPizza(XMLStreamReader reader) throws XMLStreamException {
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
        return pizza;
    }

    private Goods getXMLGoods(XMLStreamReader reader) throws XMLStreamException {
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
        return goods;
    }

    private Dough getXMLDough(XMLStreamReader reader) throws XMLStreamException {
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
        return size;
    }

    private User getXMLUser(XMLStreamReader reader) throws XMLStreamException {
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
        return user;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}

