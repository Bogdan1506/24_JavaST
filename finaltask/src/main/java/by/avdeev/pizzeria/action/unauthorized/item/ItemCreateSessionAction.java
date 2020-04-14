package by.avdeev.pizzeria.action.unauthorized.item;

import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.action.unauthorized.UnauthorizedUserAction;
import by.avdeev.pizzeria.entity.Dough;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.entity.Size;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ItemCreateSessionAction extends UnauthorizedUserAction {
    private static Logger logger = LogManager.getLogger();
    private static int counter;

    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
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
        Item item = new Item(counter++, product, dough, size);
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        if (session.getAttribute("cart") == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        cart.add(item);
        logger.debug("session cart={}", cart);
        return new ForwardObject("/product/menu");
    }
}
