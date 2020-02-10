package by.avdeev.task11.dao;

import by.avdeev.task11.bean.Component;

import java.util.List;
import java.util.Map;

public interface TextDAO {
    Component readElement(Integer key);

    List<String> readFile(String pathname) throws DAOException;

    void addTextObject(Component text);

    Map<Integer, Component> receiveTextCollection();
}
