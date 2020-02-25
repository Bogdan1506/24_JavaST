package by.avdeev.task12.creator;

import by.avdeev.task12.bean.Matrix;
import by.avdeev.task12.bean.MatrixException;
import by.avdeev.task12.service.ServiceException;

import java.util.List;

public class MatrixCreator {
    public Matrix create(List<List<String>> parsed) throws ServiceException, MatrixException {
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
        return matrix;
    }
}
