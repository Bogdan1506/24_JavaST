package by.avdeev.task12.controller.command.impl;

import by.avdeev.task12.bean.Matrix;
import by.avdeev.task12.controller.command.Command;
import by.avdeev.task12.service.MatrixService;
import by.avdeev.task12.service.ServiceException;
import by.avdeev.task12.service.ServiceFactory;
import by.avdeev.task12.service.ThreadService;
import by.avdeev.task12.service.impl.ThreadServiceImpl;
import by.avdeev.task12.view.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CyclicBarrierCommand implements Command {
    @Override
    public void execute(String pathnameToMatrix, String pathnameToNumbers) throws ServiceException {
        Logger logger = LogManager.getLogger();
        logger.debug("started");
        ServiceFactory factory = ServiceFactory.getInstance();
        ThreadService threadService = factory.getThreadService();
        MatrixService matrixService = factory.getMatrixService();
        Matrix matrix = matrixService.createMatrix(pathnameToMatrix);
        ((ThreadServiceImpl) threadService).fillCollection(matrix, pathnameToNumbers);
        threadService.doCycleBarrier(matrix);
        Printer printer = new Printer();
        printer.printMatrix(matrix);
    }
}
