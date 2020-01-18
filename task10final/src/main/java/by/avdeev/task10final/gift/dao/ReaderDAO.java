package by.avdeev.task10final.gift.dao;

import by.avdeev.task10final.gift.dao.exception.DAOException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderDAO {
    public List<String> readFile(String pathname) throws DAOException {
        List<String> resList = new ArrayList<>();
        File file = new File(pathname);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                resList.add(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            throw new DAOException(e);
        } catch (IOException e) {
            throw new DAOException(e);
        }
        return resList;
    }
}
