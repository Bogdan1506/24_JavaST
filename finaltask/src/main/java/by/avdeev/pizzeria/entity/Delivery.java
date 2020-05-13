package by.avdeev.pizzeria.entity;

import java.util.Date;
import java.util.Objects;

public class Delivery implements Entity {
    private int id;
    private OrderPosition orderPosition;
    private Date date;
    private Payment payment;

    public enum Payment {
        CASH, CARD
    }

    public Delivery() {
    }

    public Delivery(OrderPosition orderPosition, Date date, Payment payment) {
        this.orderPosition = orderPosition;
        this.date = date;
        this.payment = payment;
    }

    public Delivery(int id, OrderPosition orderPosition, Date date, Payment payment) {
        this.id = id;
        this.orderPosition = orderPosition;
        this.date = date;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderPosition getOrderPosition() {
        return orderPosition;
    }

    public void setOrderPosition(OrderPosition orderPosition) {
        this.orderPosition = orderPosition;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", orderPosition=" + orderPosition +
                ", date=" + date +
                ", payment=" + payment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return id == delivery.id &&
                Objects.equals(orderPosition, delivery.orderPosition) &&
                Objects.equals(date, delivery.date) &&
                payment == delivery.payment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderPosition, date, payment);
    }
}
