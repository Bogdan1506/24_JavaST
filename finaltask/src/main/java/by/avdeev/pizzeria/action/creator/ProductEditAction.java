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

public class ProductEditAction extends CreatorAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList("name", "description", "type", "price"));
        Map<String, Object> parameters = new HashMap<>();
        Map<String, String> invalidParameters = new HashMap<>();
        ForwardObject forwardObject = new ForwardObject("/product/edit-form");
        forwardObject.getAttributes().put("param", invalidParameters);
        boolean isValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (!isValid) {
            forwardObject.getAttributes().put(MESSAGE, "Fill all fields!");
            return forwardObject;
        }
        TypeValidator typeValidator = new ProductTypeValidator();
        boolean isProductValid = typeValidator.validate(parameters);
        if (isProductValid) {
            Validator validator = new ProductValidator();
            if (validator.validate(parameters, invalidParameters)) {
                Part filePart = request.getPart("picture");
                InputStream inputStream = filePart.getInputStream();
                parameters.put("picture", inputStream);
                Creator<Product> creator = new ProductCreator();
                Product product = creator.create(parameters);
                int id = Integer.parseInt(request.getParameter("id"));
                logger.debug("id={}", id);
                product.setId(id);
                ProductService productService = factory.getProductService();
                productService.update(product);
                forwardObject.getAttributes().put("id", Integer.parseInt(request.getParameter("id")));
                forwardObject.getAttributes().put("product", product);
                forwardObject.getAttributes().put(MESSAGE, "Changed!");
            }
        }
        return forwardObject;

    }
}
