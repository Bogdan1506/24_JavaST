package by.avdeev.task10final.textfile.controller;

import by.avdeev.task10final.textfile.controller.command.Command;
import by.avdeev.task10final.textfile.controller.command.CommandName;
import by.avdeev.task10final.textfile.controller.command.impl.AddText;
import by.avdeev.task10final.textfile.controller.command.impl.CreateFile;
import by.avdeev.task10final.textfile.controller.command.impl.PrintConsole;
import by.avdeev.task10final.textfile.controller.command.impl.RemoveFile;
import by.avdeev.task10final.textfile.controller.command.impl.RenameFile;
import by.avdeev.task10final.textfile.controller.command.impl.WrongRequest;

import java.util.EnumMap;

public class CommandProvider {
    private EnumMap<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(CommandName.CREATE, new CreateFile());
        repository.put(CommandName.RENAME, new RenameFile());
        repository.put(CommandName.PRINT, new PrintConsole());
        repository.put(CommandName.ADD_TEXT, new AddText());
        repository.put(CommandName.REMOVE, new RemoveFile());
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
