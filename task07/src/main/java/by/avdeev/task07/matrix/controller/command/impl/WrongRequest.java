package by.avdeev.task07.matrix.controller.command.impl;

import by.avdeev.task07.matrix.controller.command.Command;
import by.avdeev.task07.matrix.entity.Matrix;

public class WrongRequest implements Command {
    @Override
    public Matrix execute() {
        return null;
    }
}
