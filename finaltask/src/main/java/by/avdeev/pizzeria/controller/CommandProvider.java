package by.avdeev.pizzeria.controller;

import by.avdeev.pizzeria.command.Command;
import by.avdeev.pizzeria.command.admin.delivery.DeliveryUpdateCommand;
import by.avdeev.pizzeria.command.admin.delivery.DeliveryUpdateFormCommand;
import by.avdeev.pizzeria.command.admin.order.OrderListUpdateCommand;
import by.avdeev.pizzeria.command.admin.order.OrderProfileUpdateCommand;
import by.avdeev.pizzeria.command.admin.user.ChangeRoleCommand;
import by.avdeev.pizzeria.command.admin.user.ChangeRoleFormCommand;
import by.avdeev.pizzeria.command.admin.delivery.DeliveryListRemoveCommand;
import by.avdeev.pizzeria.command.admin.delivery.DeliveryListShowCommand;
import by.avdeev.pizzeria.command.admin.order.OrderListRemoveCommand;
import by.avdeev.pizzeria.command.admin.order.OrderListShowCommand;
import by.avdeev.pizzeria.command.admin.orderposition.OrderPositionListRemoveCommand;
import by.avdeev.pizzeria.command.admin.orderposition.OrderPositionListShowCommand;
import by.avdeev.pizzeria.command.client.profile.ProfileCreateCommand;
import by.avdeev.pizzeria.command.client.profile.ProfileUpdateCommand;
import by.avdeev.pizzeria.command.client.user.UserUpdateCommand;
import by.avdeev.pizzeria.command.creator.ProductCreateCommand;
import by.avdeev.pizzeria.command.creator.ProductCreateFormCommand;
import by.avdeev.pizzeria.command.creator.ProductEditCommand;
import by.avdeev.pizzeria.command.creator.ProductEditFormCommand;
import by.avdeev.pizzeria.command.creator.ProductRemoveCommand;
import by.avdeev.pizzeria.command.unauthorized.DeliveryFormCommand;
import by.avdeev.pizzeria.command.unauthorized.LocalizationCommand;
import by.avdeev.pizzeria.command.unauthorized.OrderCommand;
import by.avdeev.pizzeria.command.unauthorized.item.ItemCreateSessionCommand;
import by.avdeev.pizzeria.command.unauthorized.item.ItemFormCommand;
import by.avdeev.pizzeria.command.unauthorized.item.ItemRemoveCommand;
import by.avdeev.pizzeria.command.unauthorized.product.DrinkShowListCommand;
import by.avdeev.pizzeria.command.client.profile.ProfileCreateFormCommand;
import by.avdeev.pizzeria.command.admin.ProfileDeleteCommand;
import by.avdeev.pizzeria.command.client.profile.ProfileShowListCommand;
import by.avdeev.pizzeria.command.client.profile.ProfileUserShowCommand;
import by.avdeev.pizzeria.command.unauthorized.product.PizzaShowListCommand;
import by.avdeev.pizzeria.command.unauthorized.product.SidesShowListCommand;
import by.avdeev.pizzeria.command.unauthorized.user.UserCreateCommand;
import by.avdeev.pizzeria.command.admin.user.UserDeleteCommand;
import by.avdeev.pizzeria.command.unauthorized.user.UserLoginCommand;
import by.avdeev.pizzeria.command.admin.user.UserShowListCommand;
import by.avdeev.pizzeria.command.unauthorized.user.UserSignInCommand;
import by.avdeev.pizzeria.command.client.user.UserSignOutCommand;
import by.avdeev.pizzeria.command.unauthorized.user.UserSignUpCommand;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandProvider {
    private final Map<String, Command> repository = new ConcurrentHashMap<>();

    public CommandProvider() {
        repository.put("/", new PizzaShowListCommand());

        repository.put("/local", new LocalizationCommand());

        repository.put("/user/delete", new UserDeleteCommand());
        repository.put("/user/sign-up", new UserSignUpCommand());
        repository.put("/user/register", new UserCreateCommand());
        repository.put("/user/update", new UserUpdateCommand());
        repository.put("/user/login", new UserLoginCommand());
        repository.put("/user/sign-in", new UserSignInCommand());
        repository.put("/user/sign-out", new UserSignOutCommand());
        repository.put("/user/list", new UserShowListCommand());
        repository.put("/user/list/update", new ChangeRoleFormCommand());
        repository.put("/user/list/role", new ChangeRoleCommand());

        repository.put("/profile/list", new ProfileShowListCommand());
        repository.put("/profile/user", new ProfileUserShowCommand());
        repository.put("/profile/register", new ProfileCreateCommand());
        repository.put("/profile/create", new ProfileCreateFormCommand());
        repository.put("/profile/update", new ProfileUpdateCommand());
        repository.put("/profile/delete", new ProfileDeleteCommand());

        repository.put("/product/pizzas", new PizzaShowListCommand());
        repository.put("/product/drinks", new DrinkShowListCommand());
        repository.put("/product/sides", new SidesShowListCommand());
        repository.put("/product/edit-form", new ProductEditFormCommand());
        repository.put("/product/create-form", new ProductCreateFormCommand());
        repository.put("/product/edit", new ProductEditCommand());
        repository.put("/product/create", new ProductCreateCommand());
        repository.put("/product/remove", new ProductRemoveCommand());

        repository.put("/item/cart", new ItemFormCommand());
        repository.put("/item/cart/create", new ItemCreateSessionCommand());
        repository.put("/item/cart/remove", new ItemRemoveCommand());

        repository.put("/order/list", new OrderListShowCommand());
        repository.put("/order/list/remove", new OrderListRemoveCommand());
        repository.put("/order/list/update-form", new OrderListUpdateCommand());
        repository.put("/order/list/update", new OrderProfileUpdateCommand());

        repository.put("/orderposition/list", new OrderPositionListShowCommand());
        repository.put("/orderposition/list/remove", new OrderPositionListRemoveCommand());

        repository.put("/delivery/form", new DeliveryFormCommand());
        repository.put("/delivery/order", new OrderCommand());
        repository.put("/delivery/list", new DeliveryListShowCommand());
        repository.put("/delivery/list/remove", new DeliveryListRemoveCommand());
        repository.put("/delivery/list/update-form", new DeliveryUpdateFormCommand());
        repository.put("/delivery/list/update", new DeliveryUpdateCommand());
    }

    public Command receiveCommand(final String name) {
        Command command;
        command = repository.get(name);
        return command;
    }
}
