package by.avdeev.task11.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Composite implements Component {
    private final Type type;
    private List<Component> components = new ArrayList<>();

    public Composite(Type type) {
        this.type = type;
    }


    public void add(Component component) {
        components.add(component);
    }

    public List<Component> getByType(Type type) {
        List<Component> result = new ArrayList<>();
        for (Component component : components) {
            if (((Composite) component).getType() == type) {
                result.add(component);
            } else {
                List<Component> temp;
                if ((temp = ((Composite) component).getByType(type)) != null) {
                    result.addAll(temp);
                }
            }
        }
        return result;
    }

    public void delete(Component component) {
        components.remove(component);
    }

    @Override
    public String toString() {
        return "{" +
                "type=" + type +
                ", components=" + components +
                '}';
    }

    @Override
    public String collect() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Component component : components) {
            stringBuilder.append(component.collect());
            if (type == Type.SENTENCE) {
                stringBuilder.append(" ");
            }
            if (type == Type.TEXT) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public Type getType() {
        return type;
    }

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
                Objects.equals(components, composite.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, components);
    }

}
