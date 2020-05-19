package by.avdeev.pizzeria.action.unauthorized.product;

import by.avdeev.pizzeria.action.unauthorized.UnauthorizedUserAction;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.avdeev.pizzeria.action.ConstantRepository.PRODUCTS;
import static by.avdeev.pizzeria.action.ConstantRepository.MESSAGE;

public class PizzaShowListAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        ProductService productService = factory.getProductService();
        List<Product> products = productService.findByType(Product.Type.PIZZA);
        logger.debug("products={}", products);
        ForwardObject forwardObject = new ForwardObject("/product/menu");
        String message = (String) request.getAttribute(MESSAGE);
        if (message != null) {
            forwardObject.getAttributes().put(MESSAGE, message);
        }
        forwardObject.getAttributes().put(PRODUCTS, products);
        return forwardObject;
    }
}
