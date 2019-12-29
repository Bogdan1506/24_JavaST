package by.avdeev.task08.controller.command.impl;

import by.avdeev.task08.controller.command.Command;

public class WrongRequest implements Command {
    @Override
    public void execute() {
        System.out.println("There is no such request");
    }
}
