package by.avdeev.pizzeria.entity;

public enum Size {
    SMALL(1), MEDIUM(2), LARGE(3);

    int sizeId;

    Size(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getSizeId() {
        return sizeId;
    }
}
