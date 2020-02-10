package by.avdeev.task11.controller.command.impl;

import by.avdeev.task11.controller.command.Command;
import by.avdeev.task11.service.ServiceFactory;
import by.avdeev.task11.service.TextService;
import by.avdeev.task11.view.Printer;
import by.avdeev.task11.view.Reader;

import java.util.List;

public class SortWords implements Command {
    @Override
    public void execute() {
        ServiceFactory factory = ServiceFactory.getFactory();
        TextService service = factory.getTextService();
        Reader reader = new Reader();
        Printer printer = new Printer();
        String text = reader.readContent();
        List<String> strings = service.sortWords(text);
        printer.printCollection(strings);
    }
}
