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
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProductCreateAction extends CreatorAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList("name", "description", "type", "price", "picture"));
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
            boolean isParamValid = validator.validate(parameters, invalidParameters);
            if (isParamValid) {
                Creator<Product> creator = new ProductCreator();
                Product product = creator.create(parameters);
                logger.debug("product={}", product);
                ProductService productService = factory.getProductService();
                Product existProduct = productService.findByName(product.getName());
                logger.debug("existProduct={}", existProduct);
                if (existProduct == null) {
                    productService.create(product);
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
