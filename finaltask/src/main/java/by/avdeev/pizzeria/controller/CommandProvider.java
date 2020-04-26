package by.avdeev.pizzeria.controller;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.admin.ChangeRoleAction;
import by.avdeev.pizzeria.action.admin.ChangeRoleFormAction;
import by.avdeev.pizzeria.action.admin.DeliveryListRemoveAction;
import by.avdeev.pizzeria.action.admin.DeliveryListShowAction;
import by.avdeev.pizzeria.action.admin.ItemListRemoveAction;
import by.avdeev.pizzeria.action.admin.ItemListShowAction;
import by.avdeev.pizzeria.action.admin.OrderListRemoveAction;
import by.avdeev.pizzeria.action.admin.OrderListShowAction;
import by.avdeev.pizzeria.action.admin.OrderPositionListRemoveAction;
import by.avdeev.pizzeria.action.admin.OrderPositionListShowAction;
import by.avdeev.pizzeria.action.creator.ProductCreateAction;
import by.avdeev.pizzeria.action.creator.ProductCreateFormAction;
import by.avdeev.pizzeria.action.creator.ProductEditAction;
import by.avdeev.pizzeria.action.creator.ProductEditFormAction;
import by.avdeev.pizzeria.action.creator.ProductRemoveAction;
import by.avdeev.pizzeria.action.unauthorized.DeliveryFormAction;
import by.avdeev.pizzeria.action.unauthorized.OrderAction;
import by.avdeev.pizzeria.action.unauthorized.item.ItemCreateAction;
import by.avdeev.pizzeria.action.unauthorized.item.ItemCreateSessionAction;
import by.avdeev.pizzeria.action.unauthorized.item.ItemRemoveAction;
import by.avdeev.pizzeria.action.unauthorized.DrinkShowListAction;
import by.avdeev.pizzeria.action.unauthorized.MenuShowListAction;
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
import by.avdeev.pizzeria.action.admin.UserShowListAction;
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

        repository.put("/user/list", new UserShowListAction());
        repository.put("/user/list/update", new ChangeRoleFormAction());
        repository.put("/user/list/role", new ChangeRoleAction());

        repository.put("/profile/list", new ProfileShowListAction());
        repository.put("/profile/user", new ProfileUserShowAction());
        repository.put("/profile/register", new ProfileCreateAction());
        repository.put("/profile/create", new ProfileCreateFormAction());
        repository.put("/profile/update", new ProfileUpdateAction());
        repository.put("/profile/delete", new ProfileDeleteAction());

        repository.put("/product/menu", new MenuShowListAction());
        repository.put("/product/pizzas", new PizzaShowListAction());
        repository.put("/product/drinks", new DrinkShowListAction());
        repository.put("/product/sides", new SidesShowListAction());
        repository.put("/product/edit-form", new ProductEditFormAction());
        repository.put("/product/create-form", new ProductCreateFormAction());
        repository.put("/product/edit", new ProductEditAction());
        repository.put("/product/create", new ProductCreateAction());
        repository.put("/product/remove", new ProductRemoveAction());

        repository.put("/item/cart", new ItemCreateSessionAction());
        repository.put("/item/cart/order", new ItemCreateAction());
        repository.put("/item/cart/remove", new ItemRemoveAction());
        repository.put("/item/list", new ItemListShowAction());
        repository.put("/item/list/remove", new ItemListRemoveAction());

        repository.put("/order/list", new OrderListShowAction());
        repository.put("/order/list/remove", new OrderListRemoveAction());

        repository.put("/orderposition/list", new OrderPositionListShowAction());
        repository.put("/orderposition/list/remove", new OrderPositionListRemoveAction());

        repository.put("/delivery/form", new DeliveryFormAction());
        repository.put("/delivery/order", new OrderAction());
        repository.put("/delivery/list", new DeliveryListShowAction());
        repository.put("/delivery/list/remove", new DeliveryListRemoveAction());
    }

    public Action receiveCommand(String name) {
        Action action;
        action = repository.get(name);
        return action;
    }
}
