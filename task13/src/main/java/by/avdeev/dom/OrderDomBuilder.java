package by.avdeev.dom;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import by.avdeev.entity.Dough;
import by.avdeev.entity.DoughName;
import by.avdeev.entity.Goods;
import by.avdeev.entity.OrderPosition;
import by.avdeev.entity.PizzaName;
import by.avdeev.entity.Size;
import by.avdeev.entity.SizeName;
import by.avdeev.entity.User;
import by.avdeev.entity.Order;
import by.avdeev.entity.Pizza;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class OrderDomBuilder {
    private Set<Order> orders;
    private DocumentBuilder docBuilder;

    public OrderDomBuilder() {
        this.orders = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка конфигурации парсера: " + e);
        }
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void buildSetOrders(String fileName) {
        Document doc;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList orderList = root.getElementsByTagName("order");
            for (int i = 0; i < orderList.getLength(); i++) {
                Element orderElement = (Element) orderList.item(i);
                Order order = buildOrder(orderElement);
                orders.add(order);
            }
        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
    }

    private Order buildOrder(Element orderElement) {
        Order order = new Order();
        order.setId(Integer.parseInt(getElementTextContent(orderElement, "id")));
        order.setPrice(Double.parseDouble(getElementTextContent(orderElement, "price")));
        Pizza pizza = new Pizza();
        order.setPizza(pizza);
        Element pizzaElement = (Element) orderElement.getElementsByTagName("pizza").item(0);
        pizza.setId(Integer.parseInt(getElementTextContent(pizzaElement, "id")));
        Goods goods = new Goods();
        pizza.setGoods(goods);
        Element goodsElement = (Element) pizzaElement.getElementsByTagName("goods").item(0);
        goods.setId(Integer.parseInt(getElementTextContent(goodsElement, "id")));
        goods.setPrice(Double.parseDouble(getElementTextContent(goodsElement, "price")));
        goods.setName(PizzaName.valueOf(getElementTextContent(goodsElement, "name")));
        goods.setPicture(getElementTextContent(goodsElement, "picture"));
        goods.setDescription(getElementTextContent(goodsElement, "description"));
        Dough dough = new Dough();
        pizza.setDough(dough);
        Element doughElement = (Element) pizzaElement.getElementsByTagName("dough").item(0);
        dough.setId(Integer.parseInt(getElementTextContent(doughElement, "id")));
        dough.setName(DoughName.valueOf(getElementTextContent(doughElement, "name")));
        Size size = new Size();
        pizza.setSize(size);
        Element sizeElement = (Element) pizzaElement.getElementsByTagName("size").item(0);
        size.setId(Integer.parseInt(getElementTextContent(sizeElement, "id")));
        size.setName(SizeName.valueOf(getElementTextContent(sizeElement, "name")));
        size.setCoefficient(Double.parseDouble(getElementTextContent(sizeElement, "coefficient")));
        OrderPosition orderPosition = new OrderPosition();
        order.setOrderPosition(orderPosition);
        Element orderPositionElement = (Element) orderElement.getElementsByTagName("orderPosition").item(0);
        orderPosition.setId(Integer.parseInt(getElementTextContent(orderPositionElement, "id")));
        String strDate = getElementTextContent(orderPositionElement, "date");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = simpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        orderPosition.setDate(date);
        User user = new User();
        orderPosition.setUser(user);
        Element userElement = (Element) orderPositionElement.getElementsByTagName("user").item(0);
        user.setLogin(getElementTextContent(userElement, "login"));
        user.setPassword(getElementTextContent(userElement, "password"));
        user.setId(Integer.parseInt(getElementTextContent(userElement, "id")));
        user.setRole(Integer.parseInt(userElement.getAttribute("role")));
        return order;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
