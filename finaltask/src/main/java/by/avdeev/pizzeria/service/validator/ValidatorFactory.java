package by.avdeev.pizzeria.service.validator;

import by.avdeev.pizzeria.service.validator.impl.UserValidator;
import by.avdeev.pizzeria.transaction.Type;

import java.util.EnumMap;
import java.util.Map;

public class ValidatorFactory {
    private static final ValidatorFactory instance = new ValidatorFactory();
    private Map<Type, Validator> repository = new EnumMap<>(Type.class);

    private ValidatorFactory() {
        repository.put(Type.USER, new UserValidator());
        repository.put(Type.PROFILE, new UserValidator());
        repository.put(Type.PRODUCT, new UserValidator());
    }

    public static ValidatorFactory getInstance() {
        return instance;
    }

    public Validator findValidator(Type type) {
        return repository.get(type);
    }
}
