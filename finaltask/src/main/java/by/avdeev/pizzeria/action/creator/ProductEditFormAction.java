package by.avdeev.pizzeria.action.creator;

import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.avdeev.pizzeria.action.ConstantRepository.ID;
import static by.avdeev.pizzeria.action.ConstantRepository.PRODUCT;

public class ProductEditFormAction extends CreatorAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        if (request.getAttribute(PRODUCT) == null) {
            ProductService productService = factory.getProductService();
            int id = Integer.parseInt(request.getParameter(ID));
            logger.debug("id={}", id);
            Product product = productService.findById(id);
            logger.debug("product={}", product);
            request.setAttribute(PRODUCT, product);
        }
        return null;
    }
}
