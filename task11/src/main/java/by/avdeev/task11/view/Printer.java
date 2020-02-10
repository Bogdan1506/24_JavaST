package by.avdeev.task11.view;

import by.avdeev.task11.bean.Component;

import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class Printer {
    public void printTextObject(Component text) {
        out.println(text);
    }

    public void printText(String text) {
        out.println(text);
    }

    public void printMap(Map<Integer, Component> repository) {
        for (Map.Entry<Integer, Component> m : repository.entrySet()) {
            out.println(m.getKey() + " - " + m.getValue());
        }
    }

    public <T> void printCollection(List<T> components) {
        for (T component : components) {
            out.println(component);
        }
    }
}
