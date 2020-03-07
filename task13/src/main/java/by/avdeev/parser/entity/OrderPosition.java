package by.avdeev.parser.entity;

import java.util.Date;

public class OrderPosition {
    private int id;
    private Date date;
    private User user;

    public OrderPosition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "OrderPosition{" +
                "id=" + id +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
