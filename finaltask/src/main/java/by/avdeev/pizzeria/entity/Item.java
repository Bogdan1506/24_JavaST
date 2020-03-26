package by.avdeev.pizzeria.entity;

import java.util.Objects;

public class Item implements Entity {
    private int id;
    private Goods goods;
    private Dough dough;
    private Size size;

    public Item() {
    }

    public Item(int id, Goods goods, Dough dough, Size size) {
        this.id = id;
        this.goods = goods;
        this.dough = dough;
        this.size = size;
    }

    public Item(Goods goods, Dough dough, Size size) {
        this.goods = goods;
        this.dough = dough;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
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
                ", goods=" + goods +
                ", dough=" + dough +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(goods, item.goods) &&
                dough == item.dough &&
                size == item.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(goods, dough, size);
    }
}
