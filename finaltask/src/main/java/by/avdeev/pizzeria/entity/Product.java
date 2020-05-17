package by.avdeev.pizzeria.entity;

import java.util.Objects;

public class Product implements Entity {
    private int id;
    private Type type;
    private String name;
    private String description;
    private double price;
    private String picture;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", picture='" + picture + '\'' +
                '}';
    }

    public enum Type {
        PIZZA, SIDES, DRINK
    }

    public Product() {
    }

    public Product(int id, Type type, String name, String description, double price, String picture) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.price = price;
        this.picture = picture;
    }

    public Product(Type type, String name, String description, double price, String picture) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.price = price;
        this.picture = picture;
    }

    public Product(Type type, String name, String description, double price) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(String name, String description, double price, String picture) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.picture = picture;
    }

    public Product(int id, String name, String description, double price, String picture) {
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
