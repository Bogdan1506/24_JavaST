package by.avdeev.pizzeria.entity;

public enum Role {
    ADMIN(0),
    CREATOR(1),
    CLIENT(2),
    UNAUTHORIZED(3);

    private int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public static Role getByIdentity(Integer identity)
            throws IllegalArgumentException {
        Role role;
        try {
        role = Role.values()[identity];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
        return role;
    }
}
