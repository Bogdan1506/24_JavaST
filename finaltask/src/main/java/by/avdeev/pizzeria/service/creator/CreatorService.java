package by.avdeev.pizzeria.service.creator;

import by.avdeev.pizzeria.entity.Entity;
import by.avdeev.pizzeria.transaction.Type;

import java.util.EnumMap;
import java.util.Map;

public class CreatorService {
    private static final CreatorService instance = new CreatorService();
    private Map<Type, Creator<? extends Entity>> repository = new EnumMap<>(Type.class);

    private CreatorService() {
        repository.put(Type.USER, new UserCreator());
        repository.put(Type.PRODUCT, new ProductCreator());
        repository.put(Type.PROFILE, new ProfileCreator());
    }

    public static CreatorService getInstance() {
        return instance;
    }

    public Creator findCreator(Type type) {
        return repository.get(type);
    }
}
