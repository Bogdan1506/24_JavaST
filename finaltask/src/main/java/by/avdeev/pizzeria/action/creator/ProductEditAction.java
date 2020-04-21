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
        Validator<Product> validator = new ProductValidator();
        Product product = validator.validate(request);
        ProductService productService = factory.getProductService();
        productService.update(product);
        ForwardObject forwardObject = new ForwardObject("/product/edit-form");
        forwardObject.getAttributes().put("id",Integer.parseInt(request.getParameter("id")));
        forwardObject.getAttributes().put("message", "Changed!");
        return forwardObject;
    }
}
