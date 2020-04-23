package by.avdeev.pizzeria.entity;

public enum Size {
    SMALL(1, 1), MEDIUM(2, 2), LARGE(3, 3);

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

    public static Size getById(int id) {
        return Size.values()[id - 1];
    }
}
