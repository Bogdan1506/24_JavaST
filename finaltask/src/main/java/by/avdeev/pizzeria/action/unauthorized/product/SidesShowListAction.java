package by.avdeev.pizzeria.action.unauthorized.product;

import by.avdeev.pizzeria.action.unauthorized.UnauthorizedUserAction;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.avdeev.pizzeria.action.ConstantRepository.PRODUCTS;

public class SidesShowListAction extends UnauthorizedUserAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        ProductService productService = factory.getProductService();
        List<Product> products = productService.findByType(Product.Type.SIDES);
        ForwardObject forwardObject = new ForwardObject("/product/menu");
        forwardObject.getAttributes().put(PRODUCTS, products);
        return forwardObject;
    }
}
