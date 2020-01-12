package by.avdeev.task10final.calendar.controller;

import by.avdeev.task10final.calendar.controller.command.Command;
import by.avdeev.task10final.calendar.controller.command.CommandName;
import by.avdeev.task10final.calendar.controller.command.impl.AddDays;
import by.avdeev.task10final.calendar.controller.command.impl.CreateMonth;
import by.avdeev.task10final.calendar.controller.command.impl.NextDay;
import by.avdeev.task10final.calendar.controller.command.impl.Today;
import by.avdeev.task10final.calendar.controller.command.impl.WrongRequest;

import java.util.EnumMap;

public class CommandProvider {
    private EnumMap<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(CommandName.CREATE_MONTH, new CreateMonth());
        repository.put(CommandName.NEXT_DAY, new NextDay());
        repository.put(CommandName.TODAY, new Today());
        repository.put(CommandName.ADD_DAYS, new AddDays());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    public Command getCommand(String request) {
        Command command;
        try {
            CommandName commandName = CommandName.valueOf(request.toUpperCase());
            command = repository.get(commandName);
        } catch (Exception e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
