package by.avdeev.parser.entity;

public class Order {
    private int id;
    private Pizza pizza;
    private OrderPosition orderPosition;
    private double price;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public OrderPosition getOrderPosition() {
        return orderPosition;
    }

    public void setOrderPosition(OrderPosition orderPosition) {
        this.orderPosition = orderPosition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", pizza=" + pizza +
                ", orderPosition=" + orderPosition +
                ", price=" + price +
                '}';
    }
}
