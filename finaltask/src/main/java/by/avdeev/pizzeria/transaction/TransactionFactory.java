package by.avdeev.pizzeria.transaction;

public interface TransactionFactory {
    Transaction createTransaction();

    void close();
}
