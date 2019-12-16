package by.avdeev.task02.bookstore.datasource;

import by.avdeev.task02.bookstore.bean.Book;

import java.util.LinkedHashMap;
import java.util.Map;

public class BookStoreSource {
    private Map<Book, Integer> books = new LinkedHashMap<>();

    public Map<Book, Integer> getBooks() {
        return books;
    }
}
