package by.avdeev.task07.matrix.controller.command;

import by.avdeev.task07.matrix.entity.Matrix;
import by.avdeev.task07.matrix.entity.exception.MatrixException;
import by.avdeev.task07.matrix.service.exception.ServiceException;

public interface Command {
    Matrix execute() throws MatrixException, ServiceException;
}
