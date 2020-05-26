package by.avdeev.pizzeria.service.validator;

import by.avdeev.pizzeria.service.validator.impl.DeliveryValidator;
import by.avdeev.pizzeria.service.validator.impl.ProductValidator;
import by.avdeev.pizzeria.service.validator.impl.ProfileValidator;
import by.avdeev.pizzeria.service.validator.impl.UserValidator;
import by.avdeev.pizzeria.transaction.Type;

import java.util.EnumMap;
import java.util.Map;

/**
 * Factory for validator instances.
 */
public class ValidatorFactory {
    /**
     * Singleton factory instance.
     */
    private static final ValidatorFactory instance = new ValidatorFactory();
    /**
     * Keeps validator's subclasses.
     */
    private Map<Type, Validator> repository = new EnumMap<>(Type.class);

    /**
     * Creates instance for defined ${@link Type}.
     */
    private ValidatorFactory() {
        repository.put(Type.USER, new UserValidator());
        repository.put(Type.PROFILE, new ProfileValidator());
        repository.put(Type.PRODUCT, new ProductValidator());
        repository.put(Type.DELIVERY, new DeliveryValidator());
    }

    /**
     * Gives the instance.
     *
     * @return Instance of ${@link ValidatorFactory}.
     */
    public static ValidatorFactory getInstance() {
        return instance;
    }

    /**
     * Finds defined validator by ${@link Type}.
     *
     * @param type ${@link Type}.
     * @return Defined validator instance.
     */
    public Validator findValidator(Type type) {
        return repository.get(type);
    }
}