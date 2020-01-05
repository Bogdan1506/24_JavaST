package by.avdeev.task07.matrix.controller;

import by.avdeev.task07.matrix.controller.command.Command;
import by.avdeev.task07.matrix.entity.Matrix;
import by.avdeev.task07.matrix.entity.exception.MatrixException;
import by.avdeev.task07.matrix.service.exception.ServiceException;
import by.avdeev.task07.matrix.view.Printer;
import by.avdeev.task07.matrix.view.Reader;

public class Controller {
    private final CommandProvider provider = new CommandProvider();
    private final Reader reader = new Reader();
    private final Printer printer = new Printer();

    public void executeTask() throws ServiceException, MatrixException {
        String request = reader.readClient();
        Command command = provider.getCommand(request);
        Matrix matrix = command.execute();
        printer.printMatrix(matrix);
    }
}
