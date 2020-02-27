package by.avdeev.task12.creator;

import by.avdeev.task12.bean.Matrix;
import by.avdeev.task12.bean.MatrixException;
import by.avdeev.task12.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MatrixCreator {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameters are {}, {}";
    private final static String RESULT = "return value is {}";

    public Matrix create(List<List<String>> parsed) throws ServiceException, MatrixException {
        logger.debug(START);
        logger.debug(PARAM, parsed);
        int size = parsed.size();
        for (List<String> tempList : parsed) {
            if (tempList.size() != size) {
                throw new ServiceException("Incorrect size of matrix");
            }
        }
        Matrix matrix = new Matrix(size, size);
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                matrix.setElement(i, j, Integer.parseInt(parsed.get(i).get(j)));
            }
        }
        logger.debug(RESULT, matrix);
        return matrix;
    }
}
