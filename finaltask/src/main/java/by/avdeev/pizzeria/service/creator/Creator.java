package by.avdeev.pizzeria.service.creator;

import java.util.Map;

public interface Creator<T> {
    T create(Map<String, Object> parameters);
}
