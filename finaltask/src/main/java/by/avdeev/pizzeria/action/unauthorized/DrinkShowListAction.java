package by.avdeev.pizzeria.action.unauthorized;

import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DrinkShowListAction extends UnauthorizedUserAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        ProductService productService = factory.getProductService();
        List<Product> products = productService.findByType(Product.Type.DRINK);
        Forward forward = new Forward("/product/menu");
        forward.getAttributes().put("products", products);
        return forward;
    }
}
