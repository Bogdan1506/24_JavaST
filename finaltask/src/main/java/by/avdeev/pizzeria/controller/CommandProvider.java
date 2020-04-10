package by.avdeev.pizzeria.controller;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.admin.ChangeRoleAction;
import by.avdeev.pizzeria.action.admin.ChangeRoleFormAction;
import by.avdeev.pizzeria.action.unauthorized.DrinkShowListAction;
import by.avdeev.pizzeria.action.unauthorized.PizzaShowListAction;
import by.avdeev.pizzeria.action.client.profile.ProfileCreateAction;
import by.avdeev.pizzeria.action.client.profile.ProfileCreateFormAction;
import by.avdeev.pizzeria.action.client.profile.ProfileDeleteAction;
import by.avdeev.pizzeria.action.client.profile.ProfileShowListAction;
import by.avdeev.pizzeria.action.client.profile.ProfileUpdateAction;
import by.avdeev.pizzeria.action.client.profile.ProfileUserShowAction;
import by.avdeev.pizzeria.action.unauthorized.SidesShowListAction;
import by.avdeev.pizzeria.action.unauthorized.UserCreateAction;
import by.avdeev.pizzeria.action.client.user.UserDeleteAction;
import by.avdeev.pizzeria.action.unauthorized.UserLoginAction;
import by.avdeev.pizzeria.action.admin.UserShowAction;
import by.avdeev.pizzeria.action.unauthorized.UserSignInAction;
import by.avdeev.pizzeria.action.client.user.UserSignOutAction;
import by.avdeev.pizzeria.action.unauthorized.UserSignUpAction;
import by.avdeev.pizzeria.action.client.user.UserUpdateAction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandProvider {
    private final Map<String, Action> repository = new ConcurrentHashMap<>();

    public CommandProvider() {
        repository.put("/", new PizzaShowListAction());

        repository.put("/user/delete", new UserDeleteAction());
        repository.put("/user/sign-up", new UserSignUpAction());
        repository.put("/user/register", new UserCreateAction());
        repository.put("/user/update", new UserUpdateAction());
        repository.put("/user/login", new UserLoginAction());
        repository.put("/user/sign-in", new UserSignInAction());
        repository.put("/user/sign-out", new UserSignOutAction());

        repository.put("/user/list", new UserShowAction());
        repository.put("/user/list/update", new ChangeRoleFormAction());
        repository.put("/user/list/role", new ChangeRoleAction());

        repository.put("/profile/list", new ProfileShowListAction());
        repository.put("/profile/user", new ProfileUserShowAction());
        repository.put("/profile/register", new ProfileCreateAction());
        repository.put("/profile/create", new ProfileCreateFormAction());
        repository.put("/profile/update", new ProfileUpdateAction());
        repository.put("/profile/delete", new ProfileDeleteAction());

        repository.put("/product/pizzas", new PizzaShowListAction());
        repository.put("/product/drinks", new DrinkShowListAction());
        repository.put("/product/sides", new SidesShowListAction());
    }

    public Action receiveCommand(String name) {
        Action action;
        action = repository.get(name);
        return action;
    }
}
