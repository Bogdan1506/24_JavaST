package by.avdeev.pizzeria.service.security;

import org.mindrot.jbcrypt.BCrypt;

public class SecurityHandlerImpl implements SecurityHandler {
    @Override
    public String generatePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
