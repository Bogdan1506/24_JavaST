package by.avdeev.parser.entity;

public class Pizza {
    private int id;
    private Dough dough;
    private Size size;
    private Goods goods;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Pizza{" +
                "id=" + id +
                ", dough=" + dough +
                ", size=" + size +
                ", goods=" + goods +
                '}';
    }
}
