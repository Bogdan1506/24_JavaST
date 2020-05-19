package by.avdeev.pizzeria.service.security;

import org.mindrot.jbcrypt.BCrypt;

public class SecurityHandlerImpl implements SecurityHandler {
    /**
     * Generates password.
     *
     * @param password User input.
     * @return Hashed password.
     */
    @Override
    public String generatePassword(final String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Verifies passwords.
     *
     * @param plainPassword  User input.
     * @param hashedPassword Hashed password.
     * @return true if passwords match else false.
     */
    @Override
    public boolean verifyPassword(final String plainPassword,
                                  final String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
