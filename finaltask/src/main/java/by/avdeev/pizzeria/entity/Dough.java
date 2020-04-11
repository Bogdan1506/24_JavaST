package by.avdeev.pizzeria.entity;

public enum Dough {
    THIN(1), THICK(2);

    int doughId;

    Dough(int doughId) {
        this.doughId = doughId;
    }

    public int getId() {
        return doughId;
    }

    //TODO add getById()
}
