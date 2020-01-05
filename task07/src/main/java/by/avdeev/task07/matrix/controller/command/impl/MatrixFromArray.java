package by.avdeev.task07.matrix.controller.command.impl;

import by.avdeev.task07.matrix.controller.command.Command;
import by.avdeev.task07.matrix.entity.Array;
import by.avdeev.task07.matrix.entity.Matrix;
import by.avdeev.task07.matrix.service.MatrixService;
import by.avdeev.task07.matrix.service.exception.ServiceException;
import by.avdeev.task07.matrix.service.factory.ServiceFactory;
import by.avdeev.task07.matrix.view.Reader;

public class MatrixFromArray implements Command {
    @Override
    public Matrix execute() throws ServiceException {
        Reader reader = new Reader();
        ServiceFactory factory = ServiceFactory.getInstance();
        MatrixService service = factory.getMatrixService();
        Array array = reader.readArray();
        return service.fillMatrix(array);
    }
}
