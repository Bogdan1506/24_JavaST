package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.service.impl.DeliveryServiceImpl;
import by.avdeev.pizzeria.service.impl.ItemServiceImpl;
import by.avdeev.pizzeria.service.impl.OrderPositionServiceImpl;
import by.avdeev.pizzeria.service.impl.OrderServiceImpl;
import by.avdeev.pizzeria.service.impl.ProductServiceImpl;
import by.avdeev.pizzeria.service.impl.ProfileServiceImpl;
import by.avdeev.pizzeria.service.impl.UserServiceImpl;
import by.avdeev.pizzeria.transaction.DAOType;
import by.avdeev.pizzeria.transaction.TransactionFactory;

public class ServiceFactory {
    private final TransactionFactory transactionFactory;

    public ServiceFactory(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    public OrderService getOrderService() {
        OrderService orderService = new OrderServiceImpl();
        orderService.setTransaction(transactionFactory.createTransaction());
        orderService.setDAOType(DAOType.ORDER);
        return orderService;
    }

    public OrderPositionService getOrderPositionService() {
        OrderPositionService orderPositionService = new OrderPositionServiceImpl();
        orderPositionService.setTransaction(transactionFactory.createTransaction());
        orderPositionService.setDAOType(DAOType.ORDER_POSITION);
        return orderPositionService;
    }

    public DeliveryService getDeliveryService() {
        DeliveryService deliveryService = new DeliveryServiceImpl();
        deliveryService.setTransaction(transactionFactory.createTransaction());
        deliveryService.setDAOType(DAOType.DELIVERY);
        return deliveryService;
    }

    public ItemService getItemService() {
        ItemService itemService = new ItemServiceImpl();
        itemService.setTransaction(transactionFactory.createTransaction());
        itemService.setDAOType(DAOType.ITEM);
        return itemService;
    }

    public UserService getUserService() {
        UserService userService = new UserServiceImpl();
        userService.setTransaction(transactionFactory.createTransaction());
        userService.setDAOType(DAOType.USER);
        return userService;
    }

    public ProfileService getProfileService() {
        ProfileService profileService = new ProfileServiceImpl();
        profileService.setTransaction(transactionFactory.createTransaction());
        profileService.setDAOType(DAOType.PROFILE);
        return profileService;
    }

    public ProductService getProductService() {
        ProductService productService = new ProductServiceImpl();
        productService.setTransaction(transactionFactory.createTransaction());
        productService.setDAOType(DAOType.PRODUCT);
        return productService;
    }

    public TransactionFactory getTransactionFactory() {
        return transactionFactory;
    }

    public void close() throws ServiceException {
        transactionFactory.close();
    }
}
