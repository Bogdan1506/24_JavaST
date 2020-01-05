package by.avdeev.task07.matrix.controller.command.impl;

import by.avdeev.task07.matrix.controller.command.Command;
import by.avdeev.task07.matrix.entity.Matrix;
import by.avdeev.task07.matrix.entity.exception.MatrixException;
import by.avdeev.task07.matrix.service.MatrixService;
import by.avdeev.task07.matrix.service.exception.ServiceException;
import by.avdeev.task07.matrix.service.factory.ServiceFactory;
import by.avdeev.task07.matrix.view.Reader;

public class RandomMatrix implements Command {
    @Override
    public Matrix execute() throws MatrixException, ServiceException {
        Reader reader = new Reader();
        Matrix matrix = reader.readMatrix();
        ServiceFactory factory = ServiceFactory.getInstance();
        MatrixService service = factory.getMatrixService();
        service.fillRandomMatrix(matrix);
        return matrix;
    }
}
