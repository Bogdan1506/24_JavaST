package by.avdeev.pizzeria.action.unauthorized.product;

import by.avdeev.pizzeria.action.unauthorized.UnauthorizedUserAction;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MenuShowListAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        if (request.getAttribute("products") == null) {
            return new ForwardObject("/product/pizzas");
        }
        return null;
    }
}
