package by.avdeev.task07.matrix.controller.command.impl;

import by.avdeev.task07.matrix.service.exception.ServiceException;
import by.avdeev.task07.matrix.service.factory.ServiceFactory;
import by.avdeev.task07.matrix.entity.exception.MatrixException;
import by.avdeev.task07.matrix.service.MatrixService;
import by.avdeev.task07.matrix.view.Printer;
import by.avdeev.task07.matrix.view.Reader;
import by.avdeev.task07.matrix.controller.command.Command;
import by.avdeev.task07.matrix.creator.MatrixCreator;
import by.avdeev.task07.matrix.entity.Matrix;

public class ElementsSum implements Command {
    @Override
    public Matrix execute() throws MatrixException, ServiceException {
        MatrixCreator creator = new MatrixCreator();
        Reader reader = new Reader();
        Printer printer = new Printer();
        Matrix matrix = new Matrix(5, 5);
        int maxValue = reader.readMaxValue();
        creator.fillRandomized(matrix, maxValue);
        ServiceFactory factory = ServiceFactory.getInstance();
        MatrixService service = factory.getMatrixService();
        int sum = service.calculateSum(matrix);
        printer.printValue(sum);
        return matrix;
    }
}
