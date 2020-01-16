package by.avdeev.task10final.gift.controller;

import by.avdeev.task10final.gift.controller.command.Command;
import by.avdeev.task10final.gift.controller.command.CommandName;
import by.avdeev.task10final.gift.controller.command.impl.Add;
import by.avdeev.task10final.gift.controller.command.impl.Create;
import by.avdeev.task10final.gift.controller.command.impl.Find;
import by.avdeev.task10final.gift.controller.command.impl.WrongRequest;

import java.util.EnumMap;

public class CommandProvider {
    private EnumMap<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(CommandName.CREATE, new Create());
        repository.put(CommandName.ADD, new Add());
        repository.put(CommandName.FIND, new Find());
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
