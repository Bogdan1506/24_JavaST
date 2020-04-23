package by.avdeev.pizzeria.entity;

import java.util.Objects;

public class OrderPosition implements Entity {
    private int id;
    private Item item;
    private Order order;
    private double price;

    public OrderPosition() {
    }

    public OrderPosition(Item item, Order order, double price) {
        this.item = item;
        this.order = order;
        this.price = price;
    }

    public OrderPosition(int id, Item item, Order order, double price) {
        this.id = id;
        this.item = item;
        this.order = order;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderPosition{" +
                "id=" + id +
                ", item=" + item +
                ", order=" + order +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPosition that = (OrderPosition) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(item, that.item) &&
                Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, order, price);
    }
}
