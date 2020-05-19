package by.avdeev.pizzeria.service.creator;

import java.util.Map;

/**
 * Services for creating beans.
 *
 * @param <T> ${@link by.avdeev.pizzeria.entity.Entity}
 */
public interface Creator<T> {
    /**
     * Creates beans.
     *
     * @param parameters Input data from user.
     * @return Bean ${@link by.avdeev.pizzeria.entity.Entity}
     */
    T create(Map<String, Object> parameters);
}
