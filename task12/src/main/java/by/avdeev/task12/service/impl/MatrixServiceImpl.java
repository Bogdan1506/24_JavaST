package by.avdeev.task12.service.impl;

import by.avdeev.task12.bean.Matrix;
import by.avdeev.task12.bean.MatrixException;
import by.avdeev.task12.creator.MatrixCreator;
import by.avdeev.task12.dao.DAOException;
import by.avdeev.task12.dao.DAOFactory;
import by.avdeev.task12.dao.MatrixDAO;
import by.avdeev.task12.parser.MatrixParser;
import by.avdeev.task12.service.MatrixService;
import by.avdeev.task12.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MatrixServiceImpl implements MatrixService {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameter is {}";
    private final static String RESULT = "return value is {}";

    @Override
    public Matrix createMatrix(String pathname) throws ServiceException {
        logger.debug(START);
        logger.debug(PARAM, pathname);
        DAOFactory factory = DAOFactory.getInstance();
        MatrixDAO dao = factory.getMatrixDAO();
        List<String> strings;
        try {
            strings = dao.readFile(pathname);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        MatrixParser parser = new MatrixParser();
        List<List<String>> parsed = parser.parse(strings);
        MatrixCreator creator = new MatrixCreator();
        Matrix matrix;
        try {
            matrix = creator.create(parsed);
        } catch (MatrixException e) {
            throw new ServiceException(e);
        }
        logger.debug(RESULT, matrix);
        return matrix;
    }
}
