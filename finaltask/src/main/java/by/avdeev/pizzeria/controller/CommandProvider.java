package by.avdeev.pizzeria.controller;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.product.ProductShowListAction;
import by.avdeev.pizzeria.action.profile.ProfileCreateAction;
import by.avdeev.pizzeria.action.profile.ProfileCreateFormAction;
import by.avdeev.pizzeria.action.profile.ProfileDeleteAction;
import by.avdeev.pizzeria.action.profile.ProfileShowListAction;
import by.avdeev.pizzeria.action.profile.ProfileUpdateAction;
import by.avdeev.pizzeria.action.profile.ProfileUserShowAction;
import by.avdeev.pizzeria.action.user.UserSignInAction;
import by.avdeev.pizzeria.action.user.UserSignOutAction;
import by.avdeev.pizzeria.action.user.UserCreateAction;
import by.avdeev.pizzeria.action.user.UserDeleteAction;
import by.avdeev.pizzeria.action.user.UserShowAction;
import by.avdeev.pizzeria.action.user.UserLoginAction;
import by.avdeev.pizzeria.action.user.UserSignUpAction;
import by.avdeev.pizzeria.action.user.UserUpdateAction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandProvider {
    private final Map<String, Action> repository = new ConcurrentHashMap<>();

    public CommandProvider() {
        repository.put("/", new ProductShowListAction());

        repository.put("/user/list", new UserShowAction());
        repository.put("/user/delete", new UserDeleteAction());
        repository.put("/user/sign-up", new UserSignUpAction());
        repository.put("/user/register", new UserCreateAction());
        repository.put("/user/update", new UserUpdateAction());
        repository.put("/user/login", new UserLoginAction());
        repository.put("/user/sign-in", new UserSignInAction());
        repository.put("/user/sign-out", new UserSignOutAction());

        repository.put("/profile/list", new ProfileShowListAction());
        repository.put("/profile/user", new ProfileUserShowAction());
        repository.put("/profile/create", new ProfileCreateAction());
        repository.put("/profile/create-form", new ProfileCreateFormAction());
        repository.put("/profile/update", new ProfileUpdateAction());
        repository.put("/profile/delete", new ProfileDeleteAction());

        repository.put("/product/list", new ProductShowListAction());
    }

    public Action receiveCommand(String name) {
        Action action;
        action = repository.get(name);
        return action;
    }
}
