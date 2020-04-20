package by.avdeev.pizzeria.action.creator;

import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.impl.ProductValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductEditAction extends CreatorAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException, IOException, ServletException {
        System.out.println("id asdad= " + request.getParameter("id"));
        Validator<Product> validator = new ProductValidator();
        System.out.println("preriderct");
        Product product = validator.validate(request);
        ProductService productService = factory.getProductService();
        productService.update(product);
        ForwardObject forwardObject = new ForwardObject("/product/form");
        forwardObject.getAttributes().put("id",request.getParameter("id"));
        return forwardObject;
    }
}
