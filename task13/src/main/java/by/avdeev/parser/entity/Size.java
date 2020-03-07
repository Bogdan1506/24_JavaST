package by.avdeev.parser.entity;

import by.avdeev.parser.entity.enumclass.SizeName;

public class Size {
    private int id;
    private SizeName name;
    private double coefficient;

    public Size(int id, SizeName name, double coefficient) {
        this.id = id;
        this.name = name;
        this.coefficient = coefficient;
    }

    public Size() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SizeName getName() {
        return name;
    }

    public void setName(SizeName name) {
        this.name = name;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "Size{" +
                "id=" + id +
                ", name=" + name +
                ", coefficient=" + coefficient +
                '}';
    }
}
