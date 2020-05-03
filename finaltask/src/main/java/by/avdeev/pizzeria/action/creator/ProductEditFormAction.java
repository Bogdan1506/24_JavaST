package by.avdeev.pizzeria.action.creator;

import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductEditFormAction extends CreatorAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        if (request.getAttribute("product") == null) {
            ProductService productService = factory.getProductService();
            int id = Integer.parseInt(request.getParameter("id"));
            logger.debug("id={}", id);
            Product product = productService.findById(id);
            logger.debug("product={}", product);
            request.setAttribute("product", product);
        }
        return null;
    }
}
