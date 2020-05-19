package by.avdeev.pizzeria.service.security;

public interface SecurityHandler {
    String generatePassword(String password);

    boolean verifyPassword(String plainPassword, String hashedPassword);
}
