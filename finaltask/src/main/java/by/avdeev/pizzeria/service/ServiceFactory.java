package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.service.impl.ProfileServiceImpl;
import by.avdeev.pizzeria.service.impl.UserServiceImpl;
import by.avdeev.pizzeria.transaction.TransactionFactory;

public class ServiceFactory {
    private final TransactionFactory transactionFactory;
    private final UserService userService = new UserServiceImpl();
    private final ProfileService profileService = new ProfileServiceImpl();

    public ServiceFactory(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    public UserService getUserService() {
        userService.setTransaction(transactionFactory.createTransaction());
        return userService;
    }

    public ProfileService getProfileService() {
        profileService.setTransaction(transactionFactory.createTransaction());
        return profileService;
    }

    public TransactionFactory getTransactionFactory() {
        return transactionFactory;
    }

    public void close() {
        transactionFactory.close();
    }
}
