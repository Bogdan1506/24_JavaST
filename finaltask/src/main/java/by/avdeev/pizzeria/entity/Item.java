package by.avdeev.pizzeria.entity;

import java.util.Objects;

public class Item implements Entity {
    private int id;
    private Product product;
    private Dough dough;
    private Size size;

    public Item() {
    }

    public Item(int id, Product product, Dough dough, Size size) {
        this.id = id;
        this.product = product;
        this.dough = dough;
        this.size = size;
    }

    public Item(Product product, Dough dough, Size size) {
        this.product = product;
        this.dough = dough;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Dough getDough() {
        return dough;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", goods=" + product +
                ", dough=" + dough +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                Objects.equals(product, item.product) &&
                dough == item.dough &&
                size == item.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, dough, size);
    }
}
