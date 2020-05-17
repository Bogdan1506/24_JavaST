package by.avdeev.pizzeria.action.unauthorized.product;

import by.avdeev.pizzeria.action.unauthorized.UnauthorizedUserAction;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PizzaShowListAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ProductService productService = factory.getProductService();
        List<Product> products = productService.findByType(Product.Type.PIZZA);
//        logger.debug("products={}", products);
        ForwardObject forwardObject = new ForwardObject("/product/menu");
        String message = (String) request.getAttribute("message");
        if (message != null) {
            forwardObject.getAttributes().put("message", message);
        }
        forwardObject.getAttributes().put("products", products);
        return forwardObject;
    }
}
