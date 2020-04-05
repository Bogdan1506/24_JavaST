package by.avdeev.pizzeria.action.product;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductShowListAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        setName("product/product");
        ProductService productService = factory.getProductService();
        List<Product> products = productService.findAll();
        request.setAttribute("products", products);
        return null;
    }
}
