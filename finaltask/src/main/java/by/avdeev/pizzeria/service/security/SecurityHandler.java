package by.avdeev.pizzeria.service.security;

/**
 * Security supported by ${@link org.mindrot.jbcrypt.BCrypt}.
 */
public interface SecurityHandler {
    /**
     * Generates password.
     *
     * @param password User input.
     * @return Hashed password.
     */
    String generatePassword(String password);

    /**
     * Verifies passwords.
     *
     * @param plainPassword  User input.
     * @param hashedPassword Hashed password.
     * @return true if passwords match else false.
     */
    boolean verifyPassword(String plainPassword, String hashedPassword);
}
