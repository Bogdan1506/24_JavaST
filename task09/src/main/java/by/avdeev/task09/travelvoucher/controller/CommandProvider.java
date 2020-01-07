package by.avdeev.task09.travelvoucher.controller;

import by.avdeev.task09.travelvoucher.controller.command.Command;
import by.avdeev.task09.travelvoucher.controller.command.CommandName;
import by.avdeev.task09.travelvoucher.controller.command.impl.AddVoucher;
import by.avdeev.task09.travelvoucher.controller.command.impl.SelectVoucher;
import by.avdeev.task09.travelvoucher.controller.command.impl.SortByDays;
import by.avdeev.task09.travelvoucher.controller.command.impl.SortByNutrition;
import by.avdeev.task09.travelvoucher.controller.command.impl.SortByTransport;
import by.avdeev.task09.travelvoucher.controller.command.impl.SortByType;
import by.avdeev.task09.travelvoucher.controller.command.impl.WrongRequest;

import java.util.EnumMap;

public class CommandProvider {
    private EnumMap<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(CommandName.SELECT_VOUCHER, new SelectVoucher());
        repository.put(CommandName.ADD_VOUCHER, new AddVoucher());
        repository.put(CommandName.SORT_BY_TYPE, new SortByType());
        repository.put(CommandName.SORT_BY_DAYS, new SortByDays());
        repository.put(CommandName.SORT_BY_TRANSPORT, new SortByTransport());
        repository.put(CommandName.SORT_BY_NUTRITION, new SortByNutrition());

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
