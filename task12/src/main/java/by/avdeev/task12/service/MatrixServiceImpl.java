package by.avdeev.task12.service;

import by.avdeev.task12.bean.Matrix;
import by.avdeev.task12.bean.MatrixException;
import by.avdeev.task12.creator.MatrixCreator;
import by.avdeev.task12.dao.DAOException;
import by.avdeev.task12.dao.DAOFactory;
import by.avdeev.task12.dao.MatrixDAO;
import by.avdeev.task12.parser.MatrixParser;

import java.util.List;

public class MatrixServiceImpl implements MatrixService {
    @Override
    public Matrix createMatrix(String pathname) throws ServiceException {
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
        return matrix;
    }
}
