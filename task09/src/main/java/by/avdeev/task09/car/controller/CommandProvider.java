package by.avdeev.task09.car.controller;

import by.avdeev.task09.car.controller.command.Command;
import by.avdeev.task09.car.controller.command.CommandName;
import by.avdeev.task09.car.controller.command.impl.ChangeWheel;
import by.avdeev.task09.car.controller.command.impl.FindModel;
import by.avdeev.task09.car.controller.command.impl.Ride;
import by.avdeev.task09.car.controller.command.impl.Refuel;
import by.avdeev.task09.car.controller.command.impl.WrongRequest;

import java.util.EnumMap;

public class CommandProvider {
    EnumMap<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(CommandName.RIDE, new Ride());
        repository.put(CommandName.CHANGE_WHEEL, new ChangeWheel());
        repository.put(CommandName.REFUEL, new Refuel());
        repository.put(CommandName.FIND_MODEL, new FindModel());
    }

    public Command getCommand(String request) {
        Command command;
        try {
            CommandName commandName = CommandName.valueOf(request.toUpperCase());
            command = repository.get(commandName);
        } catch (NullPointerException ex) {
            command = new WrongRequest();
        }
        return command;
    }
}
