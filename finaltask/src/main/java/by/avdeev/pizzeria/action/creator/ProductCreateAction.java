package by.avdeev.pizzeria.action.creator;

import by.avdeev.pizzeria.action.validator.ProductTypeValidator;
import by.avdeev.pizzeria.action.validator.TypeValidator;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.creator.Creator;
import by.avdeev.pizzeria.service.creator.ProductCreator;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.impl.ProductValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProductCreateAction extends CreatorAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        //common
        Set<String> requiredParameters = new HashSet<>(Arrays.asList("name", "description", "type", "price"));
        Map<String, Object> parameters = new HashMap<>();
        Map<String, String> invalidParameters = new HashMap<>();

        ForwardObject forwardObjectEx = new ForwardObject("/product/create-form");
        forwardObjectEx.getAttributes().put("param", invalidParameters);

        boolean isValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (!isValid) {
            forwardObjectEx.getAttributes().put(MESSAGE, "Fill all fields!");
            return forwardObjectEx;
        }
        TypeValidator typeValidator = new ProductTypeValidator();
        boolean isProductValid = typeValidator.validate(parameters);
        logger.debug("param={}", parameters);
        logger.debug("isProductValid={}", isProductValid);
        if (isProductValid) {
            Validator validator = new ProductValidator();
            if (validator.validate(parameters, invalidParameters)) {
                Part filePart = request.getPart("picture");
                InputStream inputStream = filePart.getInputStream();
                parameters.put("picture", inputStream);
                ProductService productService = factory.getProductService();
                Creator<Product> creator = new ProductCreator();
                Product product = creator.create(parameters);
                int id = productService.create(product);
                if (id != -1) {
                    ForwardObject forwardObject = new ForwardObject("/product/pizzas");
                    forwardObject.getAttributes().put(MESSAGE, "Product is created!");
                    return forwardObject;
                } else {
                    invalidParameters.put("name", "Such name exists!");
                }
            }
        }
        return forwardObjectEx;
    }
}
