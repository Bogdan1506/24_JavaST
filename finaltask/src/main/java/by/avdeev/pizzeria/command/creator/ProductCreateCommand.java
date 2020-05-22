package by.avdeev.pizzeria.command.creator;

import by.avdeev.pizzeria.command.validator.ProductTypeValidator;
import by.avdeev.pizzeria.command.validator.TypeValidator;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import static by.avdeev.pizzeria.command.ConstantRepository.CREATED;
import static by.avdeev.pizzeria.command.ConstantRepository.DESCRIPTION;
import static by.avdeev.pizzeria.command.ConstantRepository.FILENAME;
import static by.avdeev.pizzeria.command.ConstantRepository.FILE_UPLOAD_PATH;
import static by.avdeev.pizzeria.command.ConstantRepository.FILL_FIELDS;
import static by.avdeev.pizzeria.command.ConstantRepository.INCORRECT_TYPES;
import static by.avdeev.pizzeria.command.ConstantRepository.NAME;
import static by.avdeev.pizzeria.command.ConstantRepository.NAME_EXISTS;
import static by.avdeev.pizzeria.command.ConstantRepository.PARAM;
import static by.avdeev.pizzeria.command.ConstantRepository.PICTURE;
import static by.avdeev.pizzeria.command.ConstantRepository.PRICE;
import static by.avdeev.pizzeria.command.ConstantRepository.TYPE;
import static by.avdeev.pizzeria.command.ConstantRepository.MESSAGE;

public class ProductCreateCommand extends CreatorCommand {
    private final static Logger logger = LogManager.getLogger(ProductCreateCommand.class);

    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response)
            throws ServiceException, IOException, ServletException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList(NAME, DESCRIPTION, TYPE, PRICE));
        ForwardObject forwardObjectEx = new ForwardObject("/product/create-form");
        forwardObjectEx.getAttributes().put(PARAM, invalidParameters);
        boolean isValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (!isValid) {
            forwardObjectEx.getAttributes().put(MESSAGE, FILL_FIELDS);
            return forwardObjectEx;
        }
        TypeValidator typeValidator = new ProductTypeValidator();
        boolean isProductValid = typeValidator.validate(parameters);
        logger.debug("param={}", parameters);
        logger.debug("isProductValid={}", isProductValid);
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
            int id = productService.create(parameters, invalidParameters);
            if (id != -1) {
                ForwardObject forwardObject = new ForwardObject("/product/pizzas");
                forwardObject.getAttributes().put(MESSAGE, CREATED);
                return forwardObject;
            } else {
                invalidParameters.put(NAME, NAME_EXISTS);
            }
        } else {
            forwardObjectEx.getAttributes().put(MESSAGE, INCORRECT_TYPES);
        }
        return forwardObjectEx;
    }
}
