package by.avdeev.pizzeria.service.creator;

import by.avdeev.pizzeria.entity.Product;

import java.io.InputStream;
import java.util.Map;

public class ProductCreator implements Creator<Product> {
    @Override
    public Product create(Map<String, Object> parameters) {
        String name = (String) parameters.get("name");
        String description = (String) parameters.get("description");
        InputStream picture = (InputStream) parameters.get("picture");
        double price = (double) parameters.get("price");
        Product.Type type = (Product.Type) parameters.get("type");
        return new Product(type, name, description, price, picture);
    }
}
