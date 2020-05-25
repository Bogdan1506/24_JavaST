package by.avdeev.pizzeria.command.creator;

import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_ID;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.PRODUCT;

public class ProductEditFormCommand extends CreatorCommand {
    private final static Logger logger = LogManager.getLogger(ProductEditFormCommand.class);

    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        if (request.getAttribute(PRODUCT) == null) {
            ProductService productService = factory.getProductService();
            int id = 0;
            try {
                id = Integer.parseInt(request.getParameter(ID));
            } catch (NumberFormatException e) {
                request.setAttribute(MESSAGE, INCORRECT_ID);
            }
            logger.debug("id={}", id);
            Product product = productService.findById(id);
            logger.debug("product={}", product);
            request.setAttribute(PRODUCT, product);
        }
        return null;
    }
}
