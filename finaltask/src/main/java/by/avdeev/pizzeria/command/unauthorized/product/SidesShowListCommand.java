package by.avdeev.pizzeria.command.unauthorized.product;

import by.avdeev.pizzeria.command.unauthorized.UnauthorizedCommand;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.avdeev.pizzeria.command.ConstantRepository.PRODUCTS;

public class SidesShowListCommand extends UnauthorizedCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        ProductService productService = factory.getProductService();
        List<Product> products = productService.findByType(Product.Type.SIDES);
        request.setAttribute(PRODUCTS, products);
        setName("/product/menu");
        return null;
    }
}
