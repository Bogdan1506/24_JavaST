package by.avdeev.task12.controller;

import by.avdeev.task12.controller.command.Command;
import by.avdeev.task12.controller.command.CommandName;
import by.avdeev.task12.controller.command.impl.CallableCommand;
import by.avdeev.task12.controller.command.impl.CountDownLatchCommand;
import by.avdeev.task12.controller.command.impl.CyclicBarrierCommand;
import by.avdeev.task12.controller.command.impl.ExecutorServiceCommand;
import by.avdeev.task12.controller.command.impl.PhaserCommand;
import by.avdeev.task12.controller.command.impl.WrongRequest;

import java.util.EnumMap;

public class CommandProvider {
    private final EnumMap<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(CommandName.CALLABLE, new CallableCommand());
        repository.put(CommandName.CYCLIC_BARRIER, new CyclicBarrierCommand());
        repository.put(CommandName.COUNT_DOWN_LATCH, new CountDownLatchCommand());
        repository.put(CommandName.EXECUTOR_SERVICE, new ExecutorServiceCommand());
        repository.put(CommandName.PHASER, new PhaserCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    public Command receiveCommand(String name) {
        Command command;
        try {
            CommandName commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (Exception e) {
            return repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
