package by.avdeev.pizzeria.action.creator;

import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductRemoveAction extends CreatorAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductService productService = factory.getProductService();
        productService.delete(id);
        ForwardObject forwardObject = new ForwardObject("/product/menu");
        forwardObject.getAttributes().put("message", "Product is deleted!");
        return forwardObject;
    }
}
