package by.avdeev.pizzeria.entity;

import java.util.Objects;

public class Item implements Entity {
    private int id;
    private int goodsId;
    private Dough dough;
    private Size size;

    public Item() {
    }

    public Item(int goodsId, Dough dough, Size size) {
        this.goodsId = goodsId;
        this.dough = dough;
        this.size = size;
    }

    public Item(int id, int goodsId, Dough dough, Size size) {
        this.id = id;
        this.goodsId = goodsId;
        this.dough = dough;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
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
                ", goods_id=" + goodsId +
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
                goodsId == item.goodsId &&
                dough == item.dough &&
                size == item.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsId, dough, size);
    }
}
