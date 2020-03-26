package by.avdeev.pizzeria.entity;

public enum Role {
	ADMINISTRATOR("Администратор"),
	REGISTRAR("Регистратор"),
	CREATOR("Создатель"),
	CLIENT("Клиент");

	private String name;

	private Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Integer getIdentity() {
		return ordinal();
	}

	public static Role getByIdentity(Integer identity) {
		return Role.values()[identity];
	}
}
