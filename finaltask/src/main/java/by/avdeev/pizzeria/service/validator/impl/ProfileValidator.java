package by.avdeev.pizzeria.service.validator.impl;

import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ProfileValidator implements Validator<Profile> {
    private static Logger logger = LogManager.getLogger();

    @Override
    public boolean validate(Map<String, String> parameters, Profile profile) {
        //todo patterns
        profile.setSurname(parameters.get("surname"));
        profile.setName(parameters.get("name"));
        if (parameters.get("email") != null) {
            profile.setEmail(parameters.get("email"));
        }
        profile.setPhone(parameters.get("phone"));
        profile.setAddress(parameters.get("address"));
        return true;
    }
}
