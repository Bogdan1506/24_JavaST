package by.avdeev.pizzeria.entity;

public enum Role {
    ADMINISTRATOR("Администратор", 0),
    CREATOR("Создатель", 1),
    CLIENT("Клиент", 2);

    private String name;
    private int id;

    private Role(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public static Role getByIdentity(Integer identity) {
        return Role.values()[identity];
    }
}
