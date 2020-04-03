package by.avdeev.pizzeria.controller;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.profile.ProfileCreateAction;
import by.avdeev.pizzeria.action.profile.ProfileDeleteAction;
import by.avdeev.pizzeria.action.profile.ProfileShowListAction;
import by.avdeev.pizzeria.action.profile.ProfileUpdateAction;
import by.avdeev.pizzeria.action.profile.ProfileUserShowAction;
import by.avdeev.pizzeria.action.user.UserSignOutAction;
import by.avdeev.pizzeria.action.user.UserSignUpAction;
import by.avdeev.pizzeria.action.user.UserDeleteAction;
import by.avdeev.pizzeria.action.user.UserShowAction;
import by.avdeev.pizzeria.action.user.UserSignInAction;
import by.avdeev.pizzeria.action.user.UserUpdateAction;

import java.util.HashMap;

public class CommandProvider {
    private final HashMap<String, Action> repository = new HashMap<>();

    public CommandProvider() {
        repository.put("userShowList", new UserShowAction());
        repository.put("userDelete", new UserDeleteAction());
        repository.put("userCreate", new UserSignUpAction());
        repository.put("userUpdate", new UserUpdateAction());
        repository.put("userSignIn", new UserSignInAction());
        repository.put("userSignOut", new UserSignOutAction());

        repository.put("profileShowList", new ProfileShowListAction());
        repository.put("profileUserShow", new ProfileUserShowAction());
        repository.put("profileCreate", new ProfileCreateAction());
        repository.put("profileUpdate", new ProfileUpdateAction());
        repository.put("profileDelete", new ProfileDeleteAction());
    }

    public Action receiveCommand(String name) {
        Action action;
        action = repository.get(name);
        return action;
    }
}
