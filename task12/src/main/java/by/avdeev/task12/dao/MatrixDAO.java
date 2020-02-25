package by.avdeev.task12.dao;

import java.util.List;

public interface MatrixDAO {
    List<String> readFile(String pathname) throws DAOException;
}
