package by.avdeev.pizzeria.action.unauthorized;

import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MenuShowListAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        if (request.getAttribute("products") == null) {
            return new ForwardObject("/product/pizzas");
        }
        return null;
    }
}
