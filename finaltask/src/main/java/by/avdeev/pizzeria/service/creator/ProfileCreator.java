package by.avdeev.pizzeria.service.creator;

import by.avdeev.pizzeria.entity.Profile;

import java.util.Map;

/**
 * Implementation of ${@link Creator} for ${@link Profile}.
 */
public class ProfileCreator implements Creator<Profile> {
    /**
     * @param parameters Input data from user.
     * @return Bean ${@link Profile}.
     */
    @Override
    public Profile create(final Map<String, Object> parameters) {
        String name = (String) parameters.get("name");
        String surname = (String) parameters.get("surname");
        String email = (String) parameters.get("email");
        String phone = (String) parameters.get("phone");
        String address = (String) parameters.get("address");
        return new Profile(name, surname, email, phone, address);
    }
}
