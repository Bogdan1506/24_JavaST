package by.avdeev.pizzeria.command.unauthorized.user;

import by.avdeev.pizzeria.command.unauthorized.UnauthorizedCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserSignInCommand extends UnauthorizedCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request,
                              final HttpServletResponse response) {
        return null;
    }
}
