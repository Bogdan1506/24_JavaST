package by.avdeev.task12.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MatrixDAOImpl implements MatrixDAO {
    @Override
    public List<String> readFile(String pathname) throws DAOException {
        List<String> strings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(pathname)))) {
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
