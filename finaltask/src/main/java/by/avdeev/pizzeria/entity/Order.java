package by.avdeev.pizzeria.entity;

import java.util.Date;
import java.util.Objects;

public class Order implements Entity {
    private int id;
    private Profile profile;
    private Date date;

    public Order() {
    }

    public Order(int id) {
        this.id = id;
    }

    public Order(Profile profile) {
        this.profile = profile;
    }

    public Order(int id, Profile profile, Date date) {
        this.id = id;
        this.profile = profile;
        this.date = date;
    }

    public Order(Profile profile, Date date) {
        this.profile = profile;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", profile=" + profile +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(profile, order.profile) &&
                Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profile, date);
    }
}
