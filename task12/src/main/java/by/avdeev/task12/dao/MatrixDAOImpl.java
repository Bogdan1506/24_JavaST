package by.avdeev.task12.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MatrixDAOImpl implements MatrixDAO {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameter is {}";
    private final static String RESULT = "return value is {}";

    @Override
    public List<String> readFile(String pathname) throws DAOException {
        logger.debug(START);
        logger.debug(PARAM, pathname);
        List<String> strings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(pathname)))) {
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
