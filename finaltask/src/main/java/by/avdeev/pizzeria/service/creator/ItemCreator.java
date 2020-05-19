package by.avdeev.pizzeria.service.creator;

import by.avdeev.pizzeria.entity.Dough;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.entity.Size;

import java.util.Map;

/**
 * Implementation of ${@link Creator} for ${@link Item}.
 */
public class ItemCreator implements Creator<Item> {
    /**
     * Creates ${@link Item}.
     *
     * @param parameters Input data from user.
     * @return Bean ${@link Item}
     */
    @Override
    public Item create(final Map<String, Object> parameters) {
        int id = (int) parameters.get("id");
        Product product = new Product();
        product.setId(id);
        Size size = (Size) parameters.get("size");
        Dough dough = null;
        if (parameters.get("dough") != null) {
            dough = (Dough) parameters.get("dough");
        }
        return new Item(product, dough, size);
    }
}
