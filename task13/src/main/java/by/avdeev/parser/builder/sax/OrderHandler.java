package by.avdeev.parser.builder.sax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import by.avdeev.parser.entity.Dough;
import by.avdeev.parser.entity.enumclass.DoughName;
import by.avdeev.parser.entity.Goods;
import by.avdeev.parser.entity.Order;
import by.avdeev.parser.entity.enumclass.OrderEnum;
import by.avdeev.parser.entity.OrderPosition;
import by.avdeev.parser.entity.Pizza;
import by.avdeev.parser.entity.enumclass.PizzaName;
import by.avdeev.parser.entity.Size;
import by.avdeev.parser.entity.enumclass.SizeName;
import by.avdeev.parser.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class OrderHandler extends DefaultHandler {
    private Set<Order> orders;
    private Order current = null;
    private OrderEnum currentEnum = null;
    private EnumSet<OrderEnum> withText;
    private String localName;
    private final static String ORDER = "order";
    private final static String GOODS = "goods";
    private final static String DOUGH = "dough";
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";

    public OrderHandler() {
        orders = new HashSet<>();
        withText = EnumSet.range(OrderEnum.ID, OrderEnum.DATE);
    }

    public Set<Order> getOrders() {
        return orders;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        logger.debug(START);
        logger.debug("parameters are {}, {}, {}, {}", uri, localName, qName, attrs);
        if (ORDER.equals(localName)) {
            this.localName = localName;
            current = new Order();
            return;
        }
        if ("pizza".equals(localName)) {
            this.localName = localName;
            Pizza pizza = new Pizza();
            current.setPizza(pizza);
            return;
        }
        if ("orderPosition".equals(localName)) {
            this.localName = localName;
            OrderPosition orderPosition = new OrderPosition();
            current.setOrderPosition(orderPosition);
            return;
        }
        if (GOODS.equals(localName)) {
            this.localName = localName;
            Goods goods = new Goods();
            current.getPizza().setGoods(goods);
            return;
        }
        if (DOUGH.equals(localName)) {
            this.localName = localName;
            Dough dough = new Dough();
            current.getPizza().setDough(dough);
            return;
        }
        if ("size".equals(localName)) {
            this.localName = localName;
            Size size = new Size();
            current.getPizza().setSize(size);
            return;
        }
        if ("user".equals(localName)) {
            this.localName = localName;
            User user = new User();
            user.setRole(Integer.parseInt(attrs.getValue(0)));
            current.getOrderPosition().setUser(user);
        } else {
            OrderEnum temp = OrderEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        logger.debug(START);
        logger.debug("parameters are {}, {}, {}", uri, localName, qName);
        if (ORDER.equals(localName)) {
            orders.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        logger.debug(START);
        logger.debug(ch);
        logger.debug(start);
        logger.debug(length);
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case ID:
                    switch (localName) {
                        case ORDER:
                            current.setId(Integer.parseInt(s));
                            break;
                        case "orderPosition":
                            current.getOrderPosition().setId(Integer.parseInt(s));
                            break;
                        case "user":
                            current.getOrderPosition().getUser().setId(Integer.parseInt(s));
                            break;
                        case "pizza":
                            current.getPizza().setId(Integer.parseInt(s));
                            break;
                        case GOODS:
                            current.getPizza().getGoods().setId(Integer.parseInt(s));
                            break;
                        case DOUGH:
                            current.getPizza().getDough().setId(Integer.parseInt(s));
                            break;
                        case "size":
                            current.getPizza().getSize().setId(Integer.parseInt(s));
                            break;
                        default:
                            throw new EnumConstantNotPresentException(
                                    currentEnum.getDeclaringClass(), currentEnum.name());
                    }
                    break;

                case NAME:
                    switch (localName) {
                        case GOODS:
                            current.getPizza().getGoods().setName(PizzaName.valueOf(s));
                            break;
                        case DOUGH:
                            current.getPizza().getDough().setName(DoughName.valueOf(s));
                            break;
                        case "size":
                            current.getPizza().getSize().setName(SizeName.valueOf(s));
                            break;
                        default:
                            throw new EnumConstantNotPresentException(
                                    currentEnum.getDeclaringClass(), currentEnum.name());
                    }
                    break;

                case PRICE:
                    switch (localName) {
                        case ORDER:
                            current.setPrice(Double.parseDouble(s));
                            break;
                        case GOODS:
                            current.getPizza().getGoods().setPrice(Double.parseDouble(s));
                            break;
                    }
                    break;

                case DESCRIPTION:
                    current.getPizza().getGoods().setDescription(s);
                    break;

                case DATE:
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try {
                        date = simpleDateFormat.parse(s);
                    } catch (ParseException e) {
                        logger.error(e);
                    }
                    current.getOrderPosition().setDate(date);
                    break;

                case LOGIN:
                    current.getOrderPosition().getUser().setLogin(s);
                    break;
                case PASSWORD:
                    current.getOrderPosition().getUser().setPassword(s);
                    break;
                case PICTURE:
                    current.getPizza().getGoods().setPicture(s);
                    break;
                case COEFFICIENT:
                    current.getPizza().getSize().setCoefficient(Double.parseDouble(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}
