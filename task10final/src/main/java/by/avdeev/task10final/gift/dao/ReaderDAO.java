package by.avdeev.task10final.gift.dao;

import by.avdeev.task10final.gift.dao.exception.DAOException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReaderDAO {
    public List<List<String>> readFile(String pathname) throws DAOException {
        List<String> tubList = new ArrayList<>();
        List<String> sweetsList = new ArrayList<>();
        File file = new File(pathname);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                tubList.add(reader.readLine());
                sweetsList.add(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            throw new DAOException(e);
        } catch (IOException e) {
            throw new DAOException(e);
        }
        return Arrays.asList(tubList, sweetsList);
    }
}
