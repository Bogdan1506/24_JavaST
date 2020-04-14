package by.avdeev.pizzeria.entity;

import java.sql.Date;
import java.util.Objects;

public class Delivery implements Entity {
    private int id;
    private Order order;
    private Date date;
    private Payment payment;

    public enum Payment {
        CASH, CARD
    }

    public Delivery(int id, Order order, Date date, Payment payment) {
        this.id = id;
        this.order = order;
        this.date = date;
        this.payment = payment;
    }

    public Delivery(Order order, Date date, Payment payment) {
        this.order = order;
        this.date = date;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
                ", order=" + order +
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
                Objects.equals(order, delivery.order) &&
                Objects.equals(date, delivery.date) &&
                payment == delivery.payment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, date, payment);
    }
}
