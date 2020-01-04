package by.avdeev.task07.matrixblinov.service;

import by.avdeev.task07.matrixblinov.entity.Matrix;
import by.avdeev.task07.matrixblinov.exceptions.MatrixException;

public interface MultiplicatorService {
    Matrix multiply(Matrix p, Matrix q) throws MatrixException;
}
