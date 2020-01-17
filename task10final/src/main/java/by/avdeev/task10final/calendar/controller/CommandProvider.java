package by.avdeev.task10final.calendar.controller;

import by.avdeev.task10final.calendar.controller.command.Command;
import by.avdeev.task10final.calendar.controller.command.CommandName;
import by.avdeev.task10final.calendar.controller.command.impl.AddDays;
import by.avdeev.task10final.calendar.controller.command.impl.CreateCalendar;
import by.avdeev.task10final.calendar.controller.command.impl.ShowDates;
import by.avdeev.task10final.calendar.controller.command.impl.WrongRequest;

import java.util.EnumMap;

public class CommandProvider {
    private EnumMap<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(CommandName.CREATE_CALENDAR, new CreateCalendar());
        repository.put(CommandName.SHOW, new ShowDates());
        repository.put(CommandName.ADD, new AddDays());
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
