package by.avdeev.parser.entity;

import by.avdeev.parser.entity.enumclass.PizzaName;

public class Goods {
    private int id;
    private PizzaName name;
    private String description;
    private String picture;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PizzaName getName() {
        return name;
    }

    public void setName(PizzaName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name=" + name +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                ", price=" + price +
                '}';
    }
}
