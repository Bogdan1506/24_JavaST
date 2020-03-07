package by.avdeev.stax;

public class Runner {
    public static void main(String[] args) {
        OrderStAXBuilder orderStAXBuilder = new OrderStAXBuilder();
        orderStAXBuilder.buildSetOrders("E:\\24_JavaST\\task13\\src\\main\\resources\\pizzeria.xml");
        System.out.println(orderStAXBuilder.getOrders());
    }
}
