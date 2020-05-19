package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.transaction.Type;
import by.avdeev.pizzeria.transaction.Transaction;

/**
 * declares methods for setting ${@link Transaction} and ${@link Type}.
 */
public interface UtilityService {
    /**
     * setting ${@link Transaction}.
     *
     * @param transaction ${@link Transaction}.
     */
    void setTransaction(Transaction transaction);

    /**
     * setting ${@link Type}.
     *
     * @param type ${@link Type}.
     */
    void setDAOType(Type type);
}
