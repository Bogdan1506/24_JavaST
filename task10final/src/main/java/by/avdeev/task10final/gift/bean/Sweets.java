package by.avdeev.task10final.gift.bean;

import java.util.Objects;

public class Sweets {
    private String name;
    private String firm;
    private double weight;

    public Sweets(String name, String firm, double weight) {
        this.name = name;
        this.firm = firm;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sweets sweets = (Sweets) o;
        return Double.compare(sweets.weight, weight) == 0 &&
                Objects.equals(name, sweets.name) &&
                Objects.equals(firm, sweets.firm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, firm, weight);
    }

    @Override
    public String toString() {
        return "Sweets{" +
                "name='" + name + '\'' +
                ", firm='" + firm + '\'' +
                ", weight=" + weight +
                '}';
    }
}
