package by.avdeev.pizzeria.action.creator;

import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.avdeev.pizzeria.action.ConstantRepository.ID;
import static by.avdeev.pizzeria.action.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.action.ConstantRepository.POSITION_DELETED;

public class ProductRemoveAction extends CreatorAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response)
            throws ServiceException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter(ID));
        ProductService productService = factory.getProductService();
        productService.delete(id);
        ForwardObject forwardObject = new ForwardObject("/product/menu");
        forwardObject.getAttributes().put(MESSAGE, POSITION_DELETED);
        return forwardObject;
    }
}
