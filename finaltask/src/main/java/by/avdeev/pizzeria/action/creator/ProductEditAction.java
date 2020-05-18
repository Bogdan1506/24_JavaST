package by.avdeev.pizzeria.action.creator;

import by.avdeev.pizzeria.action.validator.ProductTypeValidator;
import by.avdeev.pizzeria.action.validator.TypeValidator;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.service.ProductService;
import by.avdeev.pizzeria.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ProductEditAction extends CreatorAction {
    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException {
        Set<String> requiredParameters = new HashSet<>(Arrays.asList("name", "description", "type", "price"));
        ForwardObject forwardObject = new ForwardObject("/product/edit-form");
        forwardObject.getAttributes().put("param", invalidParameters);
        boolean isValid = TypeValidator.validateRequest(request, parameters, requiredParameters);
        if (!isValid) {
            forwardObject.getAttributes().put(MESSAGE, "Fill all fields!");
            return forwardObject;
        }
        TypeValidator typeValidator = new ProductTypeValidator();
        boolean isProductValid = typeValidator.validate(parameters);
        if (isProductValid) {
            Part part = request.getPart("picture");
            if (part.getSize() > 0) {
                String uploadFilePath = "E:\\24_JavaST\\finaltask\\web\\img";
                File fileSaveDir = new File(uploadFilePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }
                String fileName = null;
                String contentDisp = part.getHeader("content-disposition");
                String[] tokens = contentDisp.split(";");
                for (String token : tokens) {
                    if (token.trim().startsWith("filename")) {
                        fileName = token.substring(token.indexOf("=") + 2, token.length() - 1);
                    }
                }
                part.write(uploadFilePath + File.separator + fileName);
                parameters.put("picture", fileName);
            }
            ProductService productService = factory.getProductService();
            int id = productService.update(parameters, invalidParameters);
            if (id != -1) {
                Product product = productService.findById(id);
                forwardObject.getAttributes().put("id", Integer.parseInt(request.getParameter("id")));
                forwardObject.getAttributes().put("product", product);
                forwardObject.getAttributes().put(MESSAGE, "Changed!");
            }
        }
        return forwardObject;
    }
}

