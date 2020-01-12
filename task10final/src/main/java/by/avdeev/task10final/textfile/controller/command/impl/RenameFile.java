package by.avdeev.task10final.textfile.controller.command.impl;

import by.avdeev.task10final.textfile.bean.TextFile;
import by.avdeev.task10final.textfile.controller.command.Command;

public class RenameFile implements Command {
    @Override
    public void execute() {
        TextFile textFile = reader.readFile();
        String dest = reader.readFileName();
        service.rename(textFile, dest);
    }
}
