package by.avdeev.task11.service;

import by.avdeev.task11.bean.Component;

import java.util.List;

public interface SortService {
    List<Component> sortParagraphs(Component component);

    List<String> sortWords(String sentence);

    List<Component> sortLexemes(Component component, String symbol) throws ServiceException;
}
