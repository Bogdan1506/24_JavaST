package by.avdeev.task11.view;

import by.avdeev.task11.bean.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class Printer {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameter is {}";

    public void printTextObject(Component text) {
        logger.debug(START);
        logger.debug(PARAM, text);
        out.println(text);
    }

    public void printText(String text) {
        logger.debug(START);
        logger.debug(PARAM, text);
        out.println(text);
    }

    public void printMap(Map<Integer, Component> repository) {
        logger.debug(START);
        logger.debug(PARAM, repository);
        for (Map.Entry<Integer, Component> m : repository.entrySet()) {
            out.println(m.getKey() + " - " + m.getValue());
        }
    }

    public <T> void printCollection(List<T> components) {
        logger.debug(START);
        logger.debug(PARAM, components);
        for (T component : components) {
            out.println(component);
        }
    }
}
