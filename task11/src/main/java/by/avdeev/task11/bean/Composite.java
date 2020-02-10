package by.avdeev.task11.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Composite implements Component {
    private final Type type;

    public Composite(Type type) {
        this.type = type;
    }

    public Composite(Type type, String content) {
        this.type = type;
        this.content = content;
    }

    private String content;
    private List<Component> components = new ArrayList<>();

    public void add(Component component) {
        components.add(component);
    }

    @Override
    public List<Component> getByType(Type type) {
        List<Component> result = new ArrayList<>();
        for (Component component : components) {
            if (component.getType() == type) {
                result.add(component);
            } else {
                List<Component> temp = new ArrayList<>();
                if ((temp = component.getByType(type)) != null) {
                    result.addAll(temp);
                }
            }
        }
        return result;
    }

    public void addAll(List<Component> components) {
        this.components.addAll(components);
    }

    public void delete(Component component) {
        components.remove(component);
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String join() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Component component : components) {
            stringBuilder.append(component.join());
            if (type == Type.SENTENCE) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public Type getType() {
        return type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Composite composite = (Composite) o;
        return type == composite.type &&
                Objects.equals(content, composite.content) &&
                Objects.equals(components, composite.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, content, components);
    }

    @Override
    public String toString() {
        return "Composite{" +
                "type=" + type +
                ", content='" + content + '\'' +
                ", components=" + components +
                '}';
    }
}
