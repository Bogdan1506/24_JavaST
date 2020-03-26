package by.avdeev.pizzeria.controller;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.UserShowAction;

import java.util.HashMap;

public class CommandProvider {
    private final HashMap<String, Action> repository = new HashMap<>();

    public CommandProvider() {
        repository.put("showUserList", new UserShowAction());
    }

    public Action receiveCommand(String name) {
        Action action = null;
        action = repository.get(name);
        return action;
    }
}
