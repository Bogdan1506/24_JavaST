package by.avdeev.task11.controller;

import by.avdeev.task11.controller.command.Command;
import by.avdeev.task11.controller.command.CommandName;
import by.avdeev.task11.controller.command.impl.Create;
import by.avdeev.task11.controller.command.impl.Join;
import by.avdeev.task11.controller.command.impl.SortLexemes;
import by.avdeev.task11.controller.command.impl.SortParagraphs;
import by.avdeev.task11.controller.command.impl.SortWords;
import by.avdeev.task11.controller.command.impl.WrongRequest;

import java.util.EnumMap;

public class CommandProvider {
    private final EnumMap<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(CommandName.CREATE, new Create());
        repository.put(CommandName.JOIN, new Join());
        repository.put(CommandName.SORT_PARAGRAPHS, new SortParagraphs());
        repository.put(CommandName.SORT_WORDS, new SortWords());
        repository.put(CommandName.SORT_LEXEMES, new SortLexemes());
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
