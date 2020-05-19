package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.transaction.Type;
import by.avdeev.pizzeria.transaction.Transaction;

/**
 * Supports transactions.
 */
public abstract class TransactionService {
    /**
     * Contains ${@link Transaction} to maintain services with connection.
     */
    private Transaction transaction;
    /**
     * Contains type of using service.
     */
    private Type type;

    /**
     * Receiving transaction.
     *
     * @return transaction.
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * Receiving type.
     *
     * @return type.
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type of current service.
     */
    public void setDAOType(final Type type) {
        this.type = type;
    }

    /**
     * Sets transaction.
     *
     * @param transaction is maintaining connection wrapper.
     */
    public void setTransaction(final Transaction transaction) {
        this.transaction = transaction;
    }
}
