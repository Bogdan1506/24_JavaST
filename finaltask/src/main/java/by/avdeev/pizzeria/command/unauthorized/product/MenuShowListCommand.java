package by.avdeev.pizzeria.command.unauthorized.product;

import by.avdeev.pizzeria.command.unauthorized.UnauthorizedCommand;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static by.avdeev.pizzeria.command.ConstantRepository.NAME;

public class MenuShowListCommand extends UnauthorizedCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServiceException {
        ProductService productService = factory.getProductService();
        String section = request.getParameter(NAME);
        List<Product> products;
        try {
            Product.Type type = Product.Type.valueOf(section.toUpperCase());
            products = productService.findByType(type);
        } catch (IllegalArgumentException | NullPointerException e) {
            products = productService.findByType(Product.Type.PIZZA);
        }
        request.setAttribute("products", products);
        setName("/product/menu");
        return null;
    }
}
