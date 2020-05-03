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

public class ProductCreateAction extends CreatorAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException, IOException, ServletException {
        int parametersCount = 5;
        Map<String, String> parameters = new HashMap<>();
        ForwardObject forwardObjectEx = new ForwardObject("/product/create-form");
        boolean isValid = validateRequest(request, parameters, parametersCount);
        if (!isValid) {
            forwardObjectEx.getAttributes().put(MESSAGE, "Fill all fields!");
            return forwardObjectEx;
        }
        Validator<Product> validator = new ProductValidator();
        Product product = new Product();
        boolean isProductValid = validator.validate(parameters, product);
        logger.debug("product={}", product);
        logger.debug("param={}", parameters);
        logger.debug("isProductValid={}", isProductValid);
        if (isProductValid) {
            ProductService productService = factory.getProductService();
            productService.create(product);
            ForwardObject forwardObject = new ForwardObject("/product/pizzas");
            forwardObject.getAttributes().put(MESSAGE, "Product is created!");
            return forwardObject;
        } else {
            forwardObjectEx.getAttributes().put(MESSAGE, "Data is incorrect! Try again!");
            return forwardObjectEx;
        }
    }
}
