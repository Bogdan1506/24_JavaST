package by.avdeev.pizzeria.command.creator;

import by.avdeev.pizzeria.command.validator.ProductTypeValidator;
import by.avdeev.pizzeria.command.validator.TypeValidator;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static by.avdeev.pizzeria.command.ConstantRepository.CONTENT_DISPOSITION;
import static by.avdeev.pizzeria.command.ConstantRepository.DESCRIPTION;
import static by.avdeev.pizzeria.command.ConstantRepository.FILENAME;
import static by.avdeev.pizzeria.command.ConstantRepository.FILE_UPLOAD_PATH;
import static by.avdeev.pizzeria.command.ConstantRepository.FILL_FIELDS;
import static by.avdeev.pizzeria.command.ConstantRepository.ID;
import static by.avdeev.pizzeria.command.ConstantRepository.NAME;
import static by.avdeev.pizzeria.command.ConstantRepository.PARAM;
import static by.avdeev.pizzeria.command.ConstantRepository.PICTURE;
import static by.avdeev.pizzeria.command.ConstantRepository.POSITION_UPDATED;
import static by.avdeev.pizzeria.command.ConstantRepository.PRICE;
import static by.avdeev.pizzeria.command.ConstantRepository.PRODUCT;
import static by.avdeev.pizzeria.command.ConstantRepository.TYPE;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;

public class ProductEditCommand extends CreatorCommand {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response)
            throws ServiceException, IOException, ServletException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList(NAME, DESCRIPTION, TYPE, PRICE));
        ForwardObject forwardObject = new ForwardObject("/product/edit-form");
        forwardObject.getAttributes().put(PARAM, invalidParameters);
        boolean isValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (!isValid) {
            forwardObject.getAttributes().put(MESSAGE, FILL_FIELDS);
            return forwardObject;
        }
        TypeValidator typeValidator = new ProductTypeValidator();
        boolean isProductValid = typeValidator.validate(parameters);
        if (isProductValid) {
            Part part = request.getPart(PICTURE);
            if (part.getSize() > 0) {
                File fileSaveDir = new File(FILE_UPLOAD_PATH);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }
                String fileName = null;
                String contentDisposition = part.getHeader(CONTENT_DISPOSITION);
                String[] tokens = contentDisposition.split(";");
                for (String token : tokens) {
                    if (token.trim().startsWith(FILENAME)) {
                        fileName = token.substring(token.indexOf('=') + 2, token.length() - 1);
                    }
                }
                part.write(FILE_UPLOAD_PATH + File.separator + fileName);
                parameters.put(PICTURE, fileName);
            }
            ProductService productService = factory.getProductService();
            int id = productService.update(parameters, invalidParameters, Integer.parseInt((String) parameters.get(ID)));
            if (id != -1) {
                Product product = productService.findById(id);
                forwardObject.getAttributes().put(ID, Integer.parseInt(request.getParameter(ID)));
                forwardObject.getAttributes().put(PRODUCT, product);
                forwardObject.getAttributes().put(MESSAGE, POSITION_UPDATED);
            }
        }
        return forwardObject;
    }
}

