package by.avdeev.task08.dao;

import by.avdeev.task08.dao.exception.DAOException;

import java.util.List;
import java.util.stream.Stream;

public interface PhoneDAO {
    void addPhone(List<String> phones) throws DAOException;

    List<String> findAll() throws DAOException;

    Stream<String> countLines() throws DAOException;
}
