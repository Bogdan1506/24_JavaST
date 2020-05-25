package by.avdeev.pizzeria.command.creator;

import by.avdeev.pizzeria.service.image.ImageHandler;
import by.avdeev.pizzeria.service.image.ImageHandlerImpl;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.avdeev.pizzeria.command.ConstantRepository.FILE_UPLOAD_PATH;
import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_ID;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.POSITION_DELETED;
import static by.avdeev.pizzeria.command.ConstantRepository.POSITION_NOT_DELETED;

public class ProductRemoveCommand extends CreatorCommand {

    @Override
    public ForwardObject exec(final HttpServletRequest request,
                              final HttpServletResponse response)
            throws ServiceException, IOException, ServletException {
        ForwardObject forwardObject = new ForwardObject("/product/menu");
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter(ID));
        } catch (NumberFormatException e) {
            forwardObject.getAttributes().put(MESSAGE, INCORRECT_ID);
        }
        ProductService productService = factory.getProductService();
        Product product = productService.findById(id);
        if (product == null) {
            forwardObject.getAttributes().put(MESSAGE, INCORRECT_ID);
            return forwardObject;
        }
        if (productService.delete(product)) {
            String picture = product.getPicture();
            ImageHandler imageHandler = new ImageHandlerImpl();
            imageHandler.delete(picture,
                    request.getServletContext().getRealPath("") + "img");
            imageHandler.delete(picture,
                    FILE_UPLOAD_PATH);
            forwardObject.getAttributes().put(MESSAGE, POSITION_DELETED);
        } else {
            forwardObject.getAttributes().put(MESSAGE, POSITION_NOT_DELETED);
        }
        return forwardObject;
    }
}
