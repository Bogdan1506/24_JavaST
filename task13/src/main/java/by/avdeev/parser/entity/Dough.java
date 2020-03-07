package by.avdeev.parser.entity;

import by.avdeev.parser.entity.enumclass.DoughName;

public class Dough {
    private int id;
    private DoughName name;

    public Dough() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DoughName getName() {
        return name;
    }

    public void setName(DoughName name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dough{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
