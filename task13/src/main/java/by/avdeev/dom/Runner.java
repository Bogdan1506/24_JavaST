package by.avdeev.dom;

public class Runner {
    public static void main(String[] args) {
        OrderDomBuilder orderDomBuilder = new OrderDomBuilder();
        orderDomBuilder.buildSetOrders("E:\\24_JavaST\\task13\\src\\main\\resources\\pizzeria.xml");
        System.out.println(orderDomBuilder.getOrders());
    }
}
