package by.avdeev.task11.service;

import by.avdeev.task11.bean.Component;

import java.util.Map;

public interface TextService {
    Map<Integer, Component> receiveTextCollection();

    Component createTree(String pathname) throws ServiceException;

    String joinTree(Component component);

    Component findComponent(String key);
}
