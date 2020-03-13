package by.avdeev.pizzeria.entity;

import java.util.Objects;

public class Goods implements Entity {
    private int id;
    private String name;
    private String description;
    private double price;
    private String picture;

    public Goods() {
    }

    public Goods(String name, String description, double price, String picture) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.picture = picture;
    }

    public Goods(int id, String name, String description, double price, String picture) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", picture='" + picture + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return id == goods.id &&
                price == goods.price &&
                Objects.equals(name, goods.name) &&
                Objects.equals(description, goods.description) &&
                Objects.equals(picture, goods.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, picture);
    }
}
