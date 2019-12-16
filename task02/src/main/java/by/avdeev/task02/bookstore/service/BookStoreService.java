package by.avdeev.task02.bookstore.service;

import by.avdeev.task02.bookstore.bean.Book;
import by.avdeev.task02.bookstore.dao.BookStoreDAO;
import by.avdeev.task02.bookstore.service.exception.ServiceException;

import java.util.Map;

public class BookStoreService {
    private final BookStoreDAO bookStoreDAO = new BookStoreDAO();

    public Map<Book, Integer> showListOfBooks() {
        return bookStoreDAO.getAllBooks();
    }


    public int chooseBook(String name) throws ServiceException {
        Map<Book, Integer> map = bookStoreDAO.getAllBooks();
        for (Map.Entry<Book, Integer> m : map.entrySet()) {
            if (m.getKey().getName().equals(name)) {
                return m.getValue();
            }
        }
        throw new ServiceException();
    }

    public String buyBook(int payment, int price) {
        if (price == payment) return "Спасибо";
        else if (payment > price) return "Возьмите сдачу - " + (payment - price) + " рублей";
        else return "Недостаточно средств - " + (price - payment) + " рублей";
    }

    public void addBook(Book book, int price) {
        bookStoreDAO.addBook(book, price);
    }

}
