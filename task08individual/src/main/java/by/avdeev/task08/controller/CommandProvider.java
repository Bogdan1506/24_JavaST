package by.avdeev.task08.controller;

import by.avdeev.task08.controller.command.Command;
import by.avdeev.task08.controller.command.CommandName;
import by.avdeev.task08.controller.command.impl.AddPhone;
import by.avdeev.task08.controller.command.impl.FindAll;
import by.avdeev.task08.controller.command.impl.FindByInsideLineLimit;
import by.avdeev.task08.controller.command.impl.FindByOutsideLine;
import by.avdeev.task08.controller.command.impl.FindSorted;
import by.avdeev.task08.controller.command.impl.WrongRequest;

import java.util.EnumMap;

public class CommandProvider {
    private EnumMap<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(CommandName.ADD_PHONE, new AddPhone());
        repository.put(CommandName.FIND_ALL, new FindAll());
        repository.put(CommandName.FIND_BY_INSIDE_LINE_LIMIT, new FindByInsideLineLimit());
        repository.put(CommandName.FIND_BY_OUTSIDE_LINE, new FindByOutsideLine());
        repository.put(CommandName.FIND_SORTED, new FindSorted());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    public Command getCommand(String name) {
        Command command;
        CommandName commandName = null;

        try {
            commandName = commandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (Exception ex) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
