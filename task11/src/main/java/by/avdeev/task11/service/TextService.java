package by.avdeev.task11.service;

import by.avdeev.task11.bean.Component;

import java.util.List;
import java.util.Map;

public interface TextService {
    Map<Integer, Component> receiveTextCollection();

    Component createTree(String pathname) throws ServiceException;

    String joinTree(String key);
}
