package by.avdeev.pizzeria.action.unauthorized.item;

import by.avdeev.pizzeria.action.unauthorized.UnauthorizedUserAction;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ItemRemoveAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        String param = request.getParameter("id");
        if (param.equals("all")) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        } else {

            int id = Integer.parseInt(param);
            logger.debug("cart={}", cart);
            logger.debug("id={}", id);
            ListIterator<Item> itemIterator = cart.listIterator();
            cart.removeIf(item -> item.getId() == id);
        }
        return new ForwardObject("/");
    }
}
