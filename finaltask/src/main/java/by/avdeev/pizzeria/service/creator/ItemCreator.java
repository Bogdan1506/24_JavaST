package by.avdeev.pizzeria.service.creator;

import by.avdeev.pizzeria.entity.Dough;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.Product;
import by.avdeev.pizzeria.entity.Size;

import java.util.Map;

public class ItemCreator implements Creator<Item> {
    @Override
    public Item create(Map<String, Object> parameters) {
        System.out.println(3);
        System.out.println("parameters = " + parameters);
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
