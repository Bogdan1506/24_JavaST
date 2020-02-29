package by.avdeev.task11.dao;

import by.avdeev.task11.bean.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TextDAOImpl implements TextDAO {
    private Map<Integer, Component> repository = new TreeMap<>();
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameter is {}";
    private final static String RESULT = "return value is {}";

    @Override
    public Component readElement(Integer key) {
        logger.debug(START);
        logger.debug(PARAM, key);
        Component component = repository.get(key);
        logger.debug(RESULT, component);
        return component;
    }

    @Override
    public void addTextObject(Component text) {
        logger.debug(START);
        logger.debug(PARAM, text);
        int id = repository.size();
        repository.put(id, text);
    }

    @Override
    public Map<Integer, Component> receiveTextCollection() {
        logger.debug(START);
        logger.debug(RESULT, repository);
        return repository;
    }

    @Override
    public List<String> readFile(String pathname) throws DAOException {
        logger.debug(START);
        logger.debug(PARAM, pathname);
        List<String> strings = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(pathname);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream))) {
            if (fileInputStream.available() == 0) {
                throw new DAOException("File is empty");
            }
            while (reader.ready()) {
                strings.add(reader.readLine());
            }
        } catch (IOException e) {
            throw new DAOException(e);
        }
        logger.debug(RESULT, strings);
        return strings;
    }
}
