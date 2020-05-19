package by.avdeev.pizzeria.controller;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.admin.delivery.DeliveryUpdateAction;
import by.avdeev.pizzeria.action.admin.delivery.DeliveryUpdateForm;
import by.avdeev.pizzeria.action.admin.order.OrderListUpdateAction;
import by.avdeev.pizzeria.action.admin.order.OrderProfileUpdateAction;
import by.avdeev.pizzeria.action.admin.user.ChangeRoleAction;
import by.avdeev.pizzeria.action.admin.user.ChangeRoleFormAction;
import by.avdeev.pizzeria.action.admin.delivery.DeliveryListRemoveAction;
import by.avdeev.pizzeria.action.admin.delivery.DeliveryListShowAction;
import by.avdeev.pizzeria.action.admin.order.OrderListRemoveAction;
import by.avdeev.pizzeria.action.admin.order.OrderListShowAction;
import by.avdeev.pizzeria.action.admin.orderposition.OrderPositionListRemoveAction;
import by.avdeev.pizzeria.action.admin.orderposition.OrderPositionListShowAction;
import by.avdeev.pizzeria.action.client.profile.ProfileCreateAction;
import by.avdeev.pizzeria.action.client.profile.ProfileUpdateAction;
import by.avdeev.pizzeria.action.client.user.UserUpdateAction;
import by.avdeev.pizzeria.action.creator.ProductCreateAction;
import by.avdeev.pizzeria.action.creator.ProductCreateFormAction;
import by.avdeev.pizzeria.action.creator.ProductEditAction;
import by.avdeev.pizzeria.action.creator.ProductEditFormAction;
import by.avdeev.pizzeria.action.creator.ProductRemoveAction;
import by.avdeev.pizzeria.action.unauthorized.DeliveryFormAction;
import by.avdeev.pizzeria.action.unauthorized.LocalizationAction;
import by.avdeev.pizzeria.action.unauthorized.OrderAction;
import by.avdeev.pizzeria.action.unauthorized.item.ItemCreateSessionAction;
import by.avdeev.pizzeria.action.unauthorized.item.ItemFormAction;
import by.avdeev.pizzeria.action.unauthorized.item.ItemRemoveAction;
import by.avdeev.pizzeria.action.unauthorized.product.DrinkShowListAction;
import by.avdeev.pizzeria.action.unauthorized.product.MenuShowListAction;
import by.avdeev.pizzeria.action.unauthorized.product.PizzaShowListAction;
import by.avdeev.pizzeria.action.client.profile.ProfileCreateFormAction;
import by.avdeev.pizzeria.action.admin.ProfileDeleteAction;
import by.avdeev.pizzeria.action.client.profile.ProfileShowListAction;
import by.avdeev.pizzeria.action.client.profile.ProfileUserShowAction;
import by.avdeev.pizzeria.action.unauthorized.product.SidesShowListAction;
import by.avdeev.pizzeria.action.unauthorized.user.UserCreateAction;
import by.avdeev.pizzeria.action.admin.user.UserDeleteAction;
import by.avdeev.pizzeria.action.unauthorized.user.UserLoginAction;
import by.avdeev.pizzeria.action.admin.user.UserShowListAction;
import by.avdeev.pizzeria.action.unauthorized.user.UserSignInAction;
import by.avdeev.pizzeria.action.client.user.UserSignOutAction;
import by.avdeev.pizzeria.action.unauthorized.user.UserSignUpAction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandProvider {
    private final Map<String, Action> repository = new ConcurrentHashMap<>();

    public CommandProvider() {
        repository.put("/", new PizzaShowListAction());

        repository.put("/local", new LocalizationAction());

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

        repository.put("/item/cart", new ItemFormAction());
        repository.put("/item/cart/create", new ItemCreateSessionAction());
        repository.put("/item/cart/remove", new ItemRemoveAction());

        repository.put("/order/list", new OrderListShowAction());
        repository.put("/order/list/remove", new OrderListRemoveAction());
        repository.put("/order/list/update-form", new OrderListUpdateAction());
        repository.put("/order/list/update", new OrderProfileUpdateAction());

        repository.put("/orderposition/list", new OrderPositionListShowAction());
        repository.put("/orderposition/list/remove", new OrderPositionListRemoveAction());

        repository.put("/delivery/form", new DeliveryFormAction());
        repository.put("/delivery/order", new OrderAction());
        repository.put("/delivery/list", new DeliveryListShowAction());
        repository.put("/delivery/list/remove", new DeliveryListRemoveAction());
        repository.put("/delivery/list/update-form", new DeliveryUpdateForm());
        repository.put("/delivery/list/update", new DeliveryUpdateAction());
    }

    public Action receiveCommand(String name) {
        Action action;
        action = repository.get(name);
        return action;
    }
}
