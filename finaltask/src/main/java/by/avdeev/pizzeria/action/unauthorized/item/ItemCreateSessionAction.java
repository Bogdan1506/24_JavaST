package by.avdeev.pizzeria.action.unauthorized.item;

import by.avdeev.pizzeria.action.unauthorized.UnauthorizedUserAction;
import by.avdeev.pizzeria.entity.Dough;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.entity.Size;
import by.avdeev.pizzeria.service.ItemService;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ItemCreateSessionAction extends UnauthorizedUserAction {

    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ItemService itemService = factory.getItemService();
        List<Item> items = itemService.findAll();
        logger.debug("product id={}", request.getParameter("id"));
        int id = Integer.parseInt(request.getParameter("id"));
        ProductService productService = factory.getProductService();
        Product product = productService.findById(id);
        Size size = Size.valueOf(request.getParameter("size").toUpperCase());
        String doughPar = request.getParameter("dough");
        Dough dough = null;
        if (doughPar != null) {
            logger.debug("dough={}", doughPar);
            dough = Dough.valueOf(doughPar.toUpperCase());
        }
        Item item = new Item(product, dough, size);
        int itemId = 0;
        if (items.contains(item)) {
            for (Item tempItem : items) {
                if (item.equals(tempItem)) {
                    itemId = tempItem.getId();
                }
            }
        } else {
            itemId = itemService.create(item);
        }
        item.setId(itemId);
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        if (session.getAttribute("cart") == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        cart.add(item);
        logger.debug("session cart={}", cart);
        return new ForwardObject("/item/cart");
    }
}
