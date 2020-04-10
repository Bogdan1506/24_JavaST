package by.avdeev.pizzeria.action.unauthorized;

import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PizzaShowListAction extends UnauthorizedUserAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        setName("/product/menu");
        ProductService productService = factory.getProductService();
        List<Product> products = productService.findByType(Product.Type.PIZZA);
        logger.debug("products={}", products);
        request.setAttribute("products", products);
        return null;
    }
}
