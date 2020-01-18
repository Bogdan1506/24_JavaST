package by.avdeev.task10final.textfile.controller.command.impl;

import by.avdeev.task10final.gift.view.Printer;
import by.avdeev.task10final.textfile.controller.command.Command;

public class WrongRequest implements Command {
    @Override
    public void execute() {
        Printer printer = new Printer();
        printer.printError();
    }
}
