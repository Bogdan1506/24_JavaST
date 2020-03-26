package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.dao.factory.TransactionFactory;
import by.avdeev.pizzeria.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory factory = new ServiceFactory();
    private final UserService userService = new UserServiceImpl();
    private TransactionFactory transactionFactory;

    public void setTransactionFactory(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    private ServiceFactory() {
    }

    public TransactionFactory getTransactionFactory() {
        return transactionFactory;
    }

    public static ServiceFactory getFactory() {
        return factory;
    }

    public UserService getUserService() {
        return userService;
    }

    public void close() {
        transactionFactory.close();
    }
}
