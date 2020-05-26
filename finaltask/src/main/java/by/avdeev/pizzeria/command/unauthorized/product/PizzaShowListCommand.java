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

public class PizzaShowListCommand extends UnauthorizedCommand {
    private final static Logger logger = LogManager.getLogger(PizzaShowListCommand.class);

    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        ProductService productService = factory.getProductService();
        List<Product> products = productService.findByType(Product.Type.PIZZA);
        logger.debug("products={}", products);
        request.setAttribute(PRODUCTS, products);
        setName("/product/menu");
        return null;
    }
}
