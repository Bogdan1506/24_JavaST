package by.avdeev.task07.matrix.controller.command.impl;

import by.avdeev.task07.matrix.controller.command.Command;
import by.avdeev.task07.matrix.creator.MatrixCreator;
import by.avdeev.task07.matrix.entity.Matrix;
import by.avdeev.task07.matrix.entity.exception.MatrixException;
import by.avdeev.task07.matrix.service.MatrixService;
import by.avdeev.task07.matrix.service.exception.ServiceException;
import by.avdeev.task07.matrix.service.factory.ServiceFactory;
import by.avdeev.task07.matrix.view.Reader;

public class MultiplyMatrix implements Command {
    @Override
    public Matrix execute() throws MatrixException, ServiceException {
        Reader reader = new Reader();
        MatrixCreator creator = new MatrixCreator();
        int maxValue = reader.readMaxValue();
        Matrix matrix1 = reader.readMatrix();
        creator.fillRandomized(matrix1, maxValue);
        Matrix matrix2 = reader.readMatrix();
        creator.fillRandomized(matrix2, maxValue);
        ServiceFactory factory = ServiceFactory.getInstance();
        MatrixService service = factory.getMatrixService();
        return service.multiply(matrix1, matrix2);
    }
}
