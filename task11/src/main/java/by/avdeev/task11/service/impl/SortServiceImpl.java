package by.avdeev.task11.service.impl;

import by.avdeev.task11.bean.Component;
import by.avdeev.task11.bean.Composite;
import by.avdeev.task11.bean.Type;
import by.avdeev.task11.service.ServiceException;
import by.avdeev.task11.service.SortService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortServiceImpl implements SortService {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameter is {}";
    private final static String RESULT = "return value is {}";

    @Override
    public List<String> sortWords(String sentence) {
        logger.debug(START);
        logger.debug(PARAM, sentence);
        String regex = "[.,! '?]+";
        List<String> words = Arrays.asList(sentence.split(regex));
        Comparator<String> comparator = Comparator.comparingInt(String::length);
        words.sort(comparator);
        logger.debug(RESULT, words);
        return words;
    }

    @Override
    public List<Component> sortLexemes(Component component, String symbol) throws ServiceException {
        logger.debug(START);
        logger.debug("parameters are {}, {}", component, symbol);
        logger.debug(RESULT, component);
        if (symbol.length() > 1) {
            throw new ServiceException("char consists more than one symbol");
        }
        List<Component> components = ((Composite) component).getByType(Type.LEXEME);
        Comparator<Component> comparator = (o1, o2) -> {
            String strO1 = String.valueOf(o1.collect());
            String strO2 = String.valueOf(o2.collect());
            long countO1 = strO1.chars().filter(c -> c == symbol.charAt(0)).count();
            long countO2 = strO2.chars().filter(c -> c == symbol.charAt(0)).count();
            int difference = (int) (countO2 - countO1);
            if (difference == 0) {
                for (int i = 0; i < strO1.length() && i < strO2.length(); i++) {
                    if (strO1.toUpperCase().charAt(i) != strO2.toUpperCase().charAt(i)) {
                        if (strO1.charAt(i) > strO2.charAt(i)) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                }
            }
            return difference;
        };
        components.sort(comparator);
        return components;
    }

    @Override
    public List<Component> sortParagraphs(Component component) {
        logger.debug(START);
        logger.debug(PARAM, component);
        List<Component> components = ((Composite) component).getByType(Type.PARAGRAPH);
        Comparator<Component> comparator = Comparator.comparingInt(o -> ((Composite) o).getComponents().size());
        components.sort(comparator);
        logger.debug(RESULT, components);
        return components;
    }
}
