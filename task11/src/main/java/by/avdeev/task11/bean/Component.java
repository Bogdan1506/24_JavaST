package by.avdeev.task11.bean;

import java.util.List;

public interface Component {
    Object getContent();

    Type getType();

    void setComponents(List<Component> list);

    void setContent(String content);

    void addAll(List<Component> components);

    List<Component> getByType(Type type);

    List<Component> getComponents();

    String join();
}
