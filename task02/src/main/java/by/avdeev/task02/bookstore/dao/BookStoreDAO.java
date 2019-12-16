package by.avdeev.task02.bookstore.dao;

import by.avdeev.task02.bookstore.bean.Book;
import by.avdeev.task02.bookstore.datasource.BookStoreSource;

import java.util.Map;

public class BookStoreDAO {
    private final BookStoreSource source = new BookStoreSource();

    public void addBook(Book book, int price) {
        source.getBooks().put(book, price);
    }

    public Map<Book, Integer> getAllBooks() {
        return source.getBooks();
    }
}
