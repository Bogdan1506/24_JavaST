package by.avdeev.pizzeria.entity;

import java.util.Objects;

public class OrderPosition implements Entity {
    private int id;
    private Item item;
    private int orderPositionId;
    private double price;

    public OrderPosition(int id, Item item, int orderPositionId, double price) {
        this.id = id;
        this.item = item;
        this.orderPositionId = orderPositionId;
        this.price = price;
    }

    public OrderPosition(Item item, int orderPositionId, double price) {
        this.item = item;
        this.orderPositionId = orderPositionId;
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

    public int getOrderPositionId() {
        return orderPositionId;
    }

    public void setOrderPositionId(int orderPositionId) {
        this.orderPositionId = orderPositionId;
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
                ", orderPositionId=" + orderPositionId +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPosition that = (OrderPosition) o;
        return id == that.id &&
                orderPositionId == that.orderPositionId &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, orderPositionId, price);
    }
}
