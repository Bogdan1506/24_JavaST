package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.service.impl.DeliveryServiceImpl;
import by.avdeev.pizzeria.service.impl.ItemServiceImpl;
import by.avdeev.pizzeria.service.impl.OrderPositionServiceImpl;
import by.avdeev.pizzeria.service.impl.OrderServiceImpl;
import by.avdeev.pizzeria.service.impl.ProductServiceImpl;
import by.avdeev.pizzeria.service.impl.ProfileServiceImpl;
import by.avdeev.pizzeria.service.impl.UserServiceImpl;
import by.avdeev.pizzeria.transaction.TransactionFactory;
import by.avdeev.pizzeria.transaction.Type;

/**
 * Factory for getting services.
 */
public class ServiceFactory {
    /**
     * Instance of ${@link TransactionFactory}.
     */
    private final TransactionFactory transactionFactory;

    /**
     * @param transactionFactory Instance of ${@link TransactionFactory}.
     */
    public ServiceFactory(final TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    /**
     * @return Instance of ${@link OrderService}.
     */
    public OrderService getOrderService() {
        OrderService orderService = new OrderServiceImpl();
        orderService.setTransaction(transactionFactory.createTransaction());
        orderService.setDAOType(Type.ORDER);
        return orderService;
    }

    /**
     * @return Instance of ${@link OrderPositionService}.
     */
    public OrderPositionService getOrderPositionService() {
        OrderPositionService orderPositionService = new OrderPositionServiceImpl();
        orderPositionService.setTransaction(transactionFactory.createTransaction());
        orderPositionService.setDAOType(Type.ORDER_POSITION);
        return orderPositionService;
    }

    /**
     * @return Instance of ${@link DeliveryService}.
     */
    public DeliveryService getDeliveryService() {
        DeliveryService deliveryService = new DeliveryServiceImpl();
        deliveryService.setTransaction(transactionFactory.createTransaction());
        deliveryService.setDAOType(Type.DELIVERY);
        return deliveryService;
    }

    /**
     * @return Instance of ${@link ItemService}.
     */
    public ItemService getItemService() {
        ItemService itemService = new ItemServiceImpl();
        itemService.setTransaction(transactionFactory.createTransaction());
        itemService.setDAOType(Type.ITEM);
        return itemService;
    }

    /**
     * @return @return Instance of ${@link UserService}.
     */
    public UserService getUserService() {
        UserService userService = new UserServiceImpl();
        userService.setTransaction(transactionFactory.createTransaction());
        userService.setDAOType(Type.USER);
        return userService;
    }

    /**
     * @return @return Instance of ${@link ProfileService}.
     */
    public ProfileService getProfileService() {
        ProfileService profileService = new ProfileServiceImpl();
        profileService.setTransaction(transactionFactory.createTransaction());
        profileService.setDAOType(Type.PROFILE);
        return profileService;
    }

    /**
     * @return @return Instance of ${@link ProductService}.
     */
    public ProductService getProductService() {
        ProductService productService = new ProductServiceImpl();
        productService.setTransaction(transactionFactory.createTransaction());
        productService.setDAOType(Type.PRODUCT);
        return productService;
    }
}
