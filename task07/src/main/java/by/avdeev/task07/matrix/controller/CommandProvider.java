package by.avdeev.task07.matrix.controller;

import by.avdeev.task07.matrix.controller.command.Command;
import by.avdeev.task07.matrix.controller.command.CommandName;
import by.avdeev.task07.matrix.controller.command.impl.ElementsSum;
import by.avdeev.task07.matrix.controller.command.impl.MatrixFromArray;
import by.avdeev.task07.matrix.controller.command.impl.MultiplyMatrix;
import by.avdeev.task07.matrix.controller.command.impl.OneZeroMatrix;
import by.avdeev.task07.matrix.controller.command.impl.RandomMatrix;
import by.avdeev.task07.matrix.controller.command.impl.WrongRequest;

import java.util.EnumMap;

public class CommandProvider {
    EnumMap<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(CommandName.ELEMENTS_SUM, new ElementsSum());
        repository.put(CommandName.MATRIX_FROM_ARRAY, new MatrixFromArray());
        repository.put(CommandName.MULTIPLY_MATRIX, new MultiplyMatrix());
        repository.put(CommandName.ONE_ZERO_MATRIX, new OneZeroMatrix());
        repository.put(CommandName.RANDOM_MATRIX, new RandomMatrix());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    public Command getCommand(String name) {
        Command command;
        try {
            CommandName commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (Exception ex) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
