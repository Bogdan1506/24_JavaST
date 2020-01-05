package by.avdeev.task07.matrix.service.impl;

import by.avdeev.task07.matrix.entity.Array;
import by.avdeev.task07.matrix.entity.Matrix;
import by.avdeev.task07.matrix.entity.exception.ArrayException;
import by.avdeev.task07.matrix.entity.exception.MatrixException;
import by.avdeev.task07.matrix.service.MatrixService;
import by.avdeev.task07.matrix.service.exception.ServiceException;

import java.util.Random;

public class MatrixServiceImpl implements MatrixService {
    public Matrix multiply(Matrix p, Matrix q) throws MatrixException, ServiceException {
        int v = p.getVerticalSize();
        int h = q.getHorizontalSize();
        int temp = p.getHorizontalSize();
        if (temp != q.getVerticalSize()) {
            throw new MatrixException();
        }
        Matrix result = new Matrix(v, h);
        try {
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < h; j++) {
                    int value = 0;
                    for (int k = 0; k < temp; k++) {
                        value += (int) p.getElement(i, k) * (int) q.getElement(k, j);
                    }
                    result.setElement(i, j, value);
                }
            }
        } catch (MatrixException e) {
            throw new ServiceException(new MatrixException());
        }
        return result;
    }

    @Override
    public Matrix fillMatrix(Array array) throws ServiceException {
        int length = array.getLength();
        Matrix newMatrix;
        try {
            newMatrix = new Matrix(length, length);
        } catch (MatrixException e) {
            throw new ServiceException(new MatrixException());
        }
        for (int i = 0; i < newMatrix.getHorizontalSize(); ++i) {
            for (int k = 0; k < newMatrix.getHorizontalSize(); ++k) {
                try {
                    newMatrix.setElement(i, k, Math.pow(array.getElement(k), (i + 1)));
                } catch (ArrayException e) {
                    throw new ServiceException(new ArrayException());
                } catch (MatrixException e) {
                    throw new ServiceException(new MatrixException());
                }
            }
        }
        return newMatrix;
    }

    @Override
    public int calculateSum(Matrix matrix) throws ServiceException {
        int sum = 0;
        for (int i = 0; i < matrix.getHorizontalSize(); ++i) {
            for (int k = 0; k < matrix.getHorizontalSize(); ++k) {
                int element;
                try {
                    element = (int) matrix.getElement(i, k);
                } catch (MatrixException e) {
                    throw new ServiceException(new MatrixException());
                }
                if (element % 2 != 0 && element < 0) {
                    sum += Math.abs(element);
                }
            }
        }
        return sum;
    }

    @Override
    public void fillRandomMatrix(Matrix matrix) throws ServiceException {
        Random random = new Random();
        for (int i = 0; i < matrix.getHorizontalSize(); ++i) {
            int maxUnits = i;
            for (int k = 0; k < matrix.getVerticalSize(); ++k) {
                int temp = maxUnits > 0 && random.nextBoolean() ? 1 : 0;
                if (maxUnits > 0 && maxUnits >= matrix.getVerticalSize() - k) {
                    temp = 1;
                }
                if (temp == 1) {
                    --maxUnits;
                }
                try {
                    matrix.setElement(k, i, temp);
                } catch (MatrixException e) {
                    throw new ServiceException(new MatrixException());
                }
            }
        }
    }

    @Override
    public void fillMatrix(Matrix matrix) throws ServiceException {
        if (matrix.getHorizontalSize() % 2 != 0) {
            throw new ServiceException("Размерность матрицы нечетная");
        }
        for (int i = 0; i < matrix.getHorizontalSize(); ++i) {
            for (int k = 0; k < matrix.getVerticalSize(); ++k) {
                int value = 0;
                if (i == 0 || i == matrix.getHorizontalSize() - 1 || k == 0 || k == matrix.getVerticalSize() - 1) {
                    value = 1;
                }
                try {
                    matrix.setElement(k, i, value);
                } catch (MatrixException e) {
                    throw new ServiceException(new MatrixException());
                }
            }
        }
    }
}
