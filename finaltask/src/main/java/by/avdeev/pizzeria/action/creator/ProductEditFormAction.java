package by.avdeev.pizzeria.action.creator;

import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProductEditFormAction extends CreatorAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        ProductService productService = factory.getProductService();
        int id;
        HttpSession session = request.getSession(false);
        if (request.getParameter("id") == null && session != null) {
            id = (int) request.getAttribute("id");
        } else {
            id = Integer.parseInt(request.getParameter("id"));
        }
        Product product = productService.findById(id);
        request.setAttribute("product", product);
        return null;
    }
}
