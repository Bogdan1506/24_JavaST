package by.avdeev.pizzeria.action.unauthorized.product;

import by.avdeev.pizzeria.action.unauthorized.UnauthorizedUserAction;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.avdeev.pizzeria.action.ConstantRepository.PRODUCTS;

public class MenuShowListAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        if (request.getAttribute(PRODUCTS) == null) {
            return new ForwardObject("/product/pizzas");
        }
        return null;
    }
}
