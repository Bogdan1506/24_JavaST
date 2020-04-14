package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.service.impl.ItemServiceImpl;
import by.avdeev.pizzeria.service.impl.ProductServiceImpl;
import by.avdeev.pizzeria.service.impl.ProfileServiceImpl;
import by.avdeev.pizzeria.service.impl.UserServiceImpl;
import by.avdeev.pizzeria.transaction.DAOType;
import by.avdeev.pizzeria.transaction.TransactionFactory;

public class ServiceFactory {
    private final TransactionFactory transactionFactory;
    private final UserService userService = new UserServiceImpl();
    private final ProfileService profileService = new ProfileServiceImpl();
    private final ProductService productService = new ProductServiceImpl();
    private final ItemService itemService = new ItemServiceImpl();

    public ServiceFactory(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    public ItemService getItemService() {
        itemService.setTransaction(transactionFactory.createTransaction());
        itemService.setDAOType(DAOType.ITEM);
        return itemService;
    }

    public UserService getUserService() {
        userService.setTransaction(transactionFactory.createTransaction());
        userService.setDAOType(DAOType.USER);
        return userService;
    }

    public ProfileService getProfileService() {
        profileService.setTransaction(transactionFactory.createTransaction());
        profileService.setDAOType(DAOType.PROFILE);
        return profileService;
    }

    public ProductService getProductService() {
        productService.setTransaction(transactionFactory.createTransaction());
        productService.setDAOType(DAOType.PRODUCT);
        return productService;
    }

    public TransactionFactory getTransactionFactory() {
        return transactionFactory;
    }

    public void close() {
        transactionFactory.close();
    }
}
