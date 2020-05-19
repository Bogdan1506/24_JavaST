package by.avdeev.pizzeria.service.creator;

import by.avdeev.pizzeria.entity.Entity;
import by.avdeev.pizzeria.transaction.Type;

import java.util.EnumMap;
import java.util.Map;

/**
 * Factory for Creator using singleton.
 */
public final class CreatorFactory {
    /**
     * Instance of this class.
     */
    private static final CreatorFactory INSTANCE = new CreatorFactory();
    /**
     * Contains needed implementations of Creator.
     */
    private Map<Type, Creator<? extends Entity>> repository = new EnumMap<>(Type.class);

    /**
     * Private constructor putting needed implementations of Creator.
     */
    private CreatorFactory() {
        repository.put(Type.USER, new UserCreator());
        repository.put(Type.PRODUCT, new ProductCreator());
        repository.put(Type.PROFILE, new ProfileCreator());
        repository.put(Type.ITEM, new ItemCreator());
    }

    /**
     * Static method for receiving instance.
     *
     * @return Factory ${@link CreatorFactory}
     */
    public static CreatorFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Getting Creator.
     *
     * @param type ${@link Type}
     * @return Instance ${@link Creator}.
     */
    public Creator findCreator(final Type type) {
        return repository.get(type);
    }
}
