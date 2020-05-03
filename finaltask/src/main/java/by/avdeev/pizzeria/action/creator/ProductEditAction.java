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
import java.util.HashMap;
import java.util.Map;

public class ProductEditAction extends CreatorAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException, IOException, ServletException {
        int parametersCount = 6;
        Map<String, String> parameters = new HashMap<>();
        ForwardObject forwardObjectEx = new ForwardObject("/product/edit-form");
        boolean isValid = validateRequest(request, parameters, parametersCount);
        if (!isValid) {
            forwardObjectEx.getAttributes().put(MESSAGE, "Fill all fields!");
            return forwardObjectEx;
        }
        Validator<Product> validator = new ProductValidator();
        ForwardObject forwardObject = new ForwardObject("/product/edit-form");
        Product product = new Product();
        int id = Integer.parseInt(request.getParameter("id"));
        logger.debug("id={}", id);
//        forwardObject.getAttributes().put("id", id);
        product.setId(id);
        boolean isProductValid = validator.validate(parameters, product);
        forwardObject.getAttributes().put("product", product);
        if (isProductValid) {
            ProductService productService = factory.getProductService();
            productService.update(product);
            forwardObject.getAttributes().put("id", Integer.parseInt(request.getParameter("id")));
            forwardObject.getAttributes().put(MESSAGE, "Changed!");
        } else {
            forwardObject.getAttributes().put(MESSAGE, "Data is incorrect! Try again!");
        }
        return forwardObject;

    }
}
