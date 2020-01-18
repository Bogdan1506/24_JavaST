package by.avdeev.task10final.calendar.controller.command.impl;

import by.avdeev.task10final.calendar.controller.command.Command;
import by.avdeev.task10final.gift.view.Printer;

public class WrongRequest implements Command {
    @Override
    public void execute() {
        Printer printer = new Printer();
        printer.printError();
    }
}
