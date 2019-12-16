package by.avdeev.task02.bookstore.controller;

import by.avdeev.task02.bookstore.bean.Book;
import by.avdeev.task02.bookstore.reader.Reader;
import by.avdeev.task02.bookstore.service.BookStoreService;
import by.avdeev.task02.bookstore.service.exception.ServiceException;

import java.util.Map;

import static java.lang.System.out;

public class Controller { //№ 34
    private final Reader reader = new Reader();
    private final BookStoreService bookStoreService = new BookStoreService();

    public void executeTask() throws ServiceException {
        Map<Book, Integer> books = bookStoreService.showListOfBooks();
        for (Map.Entry<Book, Integer> book : books.entrySet()) {
            out.println("\t" + book.getKey() + " - " + book.getValue());
        }
        String bookName = reader.read();
        int price = bookStoreService.chooseBook(bookName);
        out.printf("\"%s\" стоит %d рублей. ", bookName, price);
        int payment = reader.readPrice();
        out.println(bookStoreService.buyBook(payment, price));
    }

    public static void main(String[] args) throws ServiceException {
        Book book = new Book("Thinking in Java", "Bruce Eckel");
        Controller controller = new Controller();
        controller.bookStoreService.addBook(book, 800);
        controller.executeTask();
    }
}
