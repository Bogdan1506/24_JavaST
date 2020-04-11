package by.avdeev.pizzeria.entity;

public enum Size {
    SMALL(1, 1.0), MEDIUM(2, 1.5), LARGE(3, 2.0);

    int sizeId;
    double coefficient;

    Size(int sizeId, double coefficient) {
        this.sizeId = sizeId;
        this.coefficient = coefficient;
    }

    public int getId() {
        return sizeId;
    }

    public double getCoefficient() {
        return coefficient;
    }
}
