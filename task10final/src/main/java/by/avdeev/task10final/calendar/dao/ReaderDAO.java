package by.avdeev.task10final.calendar.dao;

import by.avdeev.task10final.calendar.dao.exception.DAOException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderDAO {
    public List<String> readDates(File file) throws DAOException {
        List<String> strDates = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                strDates.add(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            throw new DAOException(e);
        } catch (IOException e) {
            throw new DAOException(e);
        }
        return strDates;
    }
}
