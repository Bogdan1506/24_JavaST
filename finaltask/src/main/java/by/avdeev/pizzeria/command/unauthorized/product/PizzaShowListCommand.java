package by.avdeev.pizzeria.command.unauthorized.product;

import by.avdeev.pizzeria.command.unauthorized.UnauthorizedCommand;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.avdeev.pizzeria.command.ConstantRepository.PRODUCTS;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;

public class PizzaShowListCommand extends UnauthorizedCommand {
    private final static Logger logger2 = LogManager.getLogger(PizzaShowListCommand.class);

    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        ProductService productService = factory.getProductService();
        List<Product> products = productService.findByType(Product.Type.PIZZA);
        logger2.debug("products={}", products);
        ForwardObject forwardObject = new ForwardObject("/product/menu");
        String message = (String) request.getAttribute(MESSAGE);
        if (message != null) {
            forwardObject.getAttributes().put(MESSAGE, message);
        }
        forwardObject.getAttributes().put(PRODUCTS, products);
        return forwardObject;
    }
}
