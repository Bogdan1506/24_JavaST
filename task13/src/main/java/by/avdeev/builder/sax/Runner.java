package by.avdeev.builder.sax;

public class Runner {
    public static void main(String[] args) {
        OrderSAXBuilder saxBuilder = new OrderSAXBuilder();
        saxBuilder.buildSetStudents("E:\\24_JavaST\\task13\\src\\main\\resources\\pizzeria.xml");
        System.out.println(saxBuilder.getStudents());
    }
}
