package by.avdeev.pizzeria.command.creator;

import by.avdeev.pizzeria.service.image.ImageHandler;
import by.avdeev.pizzeria.service.image.ImageHandlerImpl;
import by.avdeev.pizzeria.command.validator.ProductTypeValidator;
import by.avdeev.pizzeria.command.validator.TypeValidator;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static by.avdeev.pizzeria.command.ConstantRepository.DESCRIPTION;
import static by.avdeev.pizzeria.command.ConstantRepository.FILE_UPLOAD_PATH;
import static by.avdeev.pizzeria.command.ConstantRepository.FILL_FIELDS;
import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_ID;
import static by.avdeev.pizzeria.command.ConstantRepository.INVALID_IMAGE;
import static by.avdeev.pizzeria.command.ConstantRepository.NAME;
import static by.avdeev.pizzeria.command.ConstantRepository.PARAM;
import static by.avdeev.pizzeria.command.ConstantRepository.PICTURE;
import static by.avdeev.pizzeria.command.ConstantRepository.POSITION_NOT_UPDATED;
import static by.avdeev.pizzeria.command.ConstantRepository.POSITION_UPDATED;
import static by.avdeev.pizzeria.command.ConstantRepository.PRICE;
import static by.avdeev.pizzeria.command.ConstantRepository.PRODUCT;
import static by.avdeev.pizzeria.command.ConstantRepository.TYPE;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;

public class ProductEditCommand extends CreatorCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response)
            throws ServiceException, IOException, ServletException {
        ForwardObject forwardObject = new ForwardObject("/product/edit-form?id=" + request.getParameter(ID));
        Set<String> requiredParameters = new HashSet<>(Arrays.asList(NAME, DESCRIPTION, TYPE, PRICE));
        forwardObject.getAttributes().put(PARAM, invalidParameters);
        boolean isValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (!isValid) {
            forwardObject.getAttributes().put(MESSAGE, FILL_FIELDS);
            return forwardObject;
        }
        TypeValidator typeValidator = new ProductTypeValidator();
        boolean isProductValid = typeValidator.validate(parameters);
        if (isProductValid) {
            ProductService productService = factory.getProductService();
            int id;
            try {
                id = Integer.parseInt((String) parameters.get(ID));
            } catch (NumberFormatException e) {
                forwardObject.getAttributes().put(MESSAGE, INCORRECT_ID);
                return forwardObject;
            }
            id = productService.update(parameters, invalidParameters, id);
            Product product = productService.findById(id);
            Part part = request.getPart(PICTURE);
            if (part.getSize() > 0) {
                String picture = product.getPicture();
                ImageHandler imageHandler = new ImageHandlerImpl();
                imageHandler.delete(picture,
                        request.getServletContext().getRealPath("") + "img");
                imageHandler.delete(picture,
                        FILE_UPLOAD_PATH);
                String fileName = imageHandler.receiveImageName(part);
                if (!imageHandler.validate(fileName)) {
                    forwardObject.getAttributes().put(MESSAGE, INVALID_IMAGE);
                    return forwardObject;
                }
                if (fileName != null) {
                    String srcPath = request.getServletContext().getRealPath("") + "img";
                    boolean isUploaded = imageHandler.upload(part,
                            srcPath,
                            fileName);
                    if (isUploaded) {
                        boolean isCopied = imageHandler.copy(srcPath,
                                FILE_UPLOAD_PATH, fileName);
                        if (isCopied) {
                            parameters.put(PICTURE, fileName);
                        }
                    }
                }
            }
            id = productService.update(parameters, invalidParameters, id);
            if (id != -1) {
                forwardObject.getAttributes().put(ID, id);
                forwardObject.getAttributes().put(PRODUCT, product);
                forwardObject.getAttributes().put(MESSAGE, POSITION_UPDATED);
            } else {
                forwardObject.getAttributes().put(MESSAGE, POSITION_NOT_UPDATED);
            }
        }
        return forwardObject;
    }
}

