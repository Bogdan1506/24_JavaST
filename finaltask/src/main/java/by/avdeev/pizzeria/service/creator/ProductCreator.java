package by.avdeev.pizzeria.service.creator;

import by.avdeev.pizzeria.entity.Product;

import java.util.Map;

/**
 * Implementation of ${@link Creator} for ${@link Product}.
 */
public class ProductCreator implements Creator<Product> {
    /**
     * @param parameters Input data from user.
     * @return Bean ${@link Product}.
     */
    @Override
    public Product create(final Map<String, Object> parameters) {
        String name = (String) parameters.get("name");
        String description = (String) parameters.get("description");
        double price = (double) parameters.get("price");
        Product.Type type = (Product.Type) parameters.get("type");
        String picture = (String) parameters.get("picture");
        if (picture != null) {
            return new Product(type, name, description, price, picture);
        } else {
            return new Product(type, name, description, price);
        }
    }
}
