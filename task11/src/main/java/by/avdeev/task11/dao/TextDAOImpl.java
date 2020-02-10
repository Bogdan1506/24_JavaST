package by.avdeev.task11.dao;

import by.avdeev.task11.bean.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TextDAOImpl implements TextDAO {
    private Map<Integer, Component> repository = new TreeMap<>();

    @Override
    public Component readElement(Integer key) {
        return repository.get(key);
    }

    @Override
    public void addTextObject(Component text) {
        int id = repository.size();
        repository.put(id, text);
    }

    @Override
    public Map<Integer, Component> receiveTextCollection() {
        return repository;
    }

    @Override
    public List<String> readFile(String pathname) throws DAOException {
        List<String> strings = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(pathname);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream))) {
            while (reader.ready()) {
                strings.add(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            throw new DAOException(e);
        } catch (IOException e) {
            throw new DAOException(e);
        }
        return strings;
    }
}
