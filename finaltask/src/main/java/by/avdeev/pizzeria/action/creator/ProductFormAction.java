package by.avdeev.pizzeria.action.creator;

import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProductFormAction extends CreatorAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        ProductService productService = factory.getProductService();
        int id;
        if (request.getParameter("id") == null) {
            HttpSession session = request.getSession();
            id = (int) session.getAttribute("id");
        } else {
            id = Integer.parseInt(request.getParameter("id"));
        }
        Product product = productService.findById(id);
        request.setAttribute("product", product);
        return null;
    }
}
