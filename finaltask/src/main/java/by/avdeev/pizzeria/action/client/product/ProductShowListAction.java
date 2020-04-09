package by.avdeev.pizzeria.action.client.product;

import by.avdeev.pizzeria.action.client.ClientAction;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductShowListAction extends ClientAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        setName("/product/product");
        ProductService productService = factory.getProductService();
        List<Product> products = productService.findAll();
        request.setAttribute("products", products);
        return null;
    }
}
