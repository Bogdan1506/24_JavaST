package by.avdeev.parser.service;

import by.avdeev.parser.entity.Order;

import java.util.Set;

public interface ParserService {
    Set<Order> parse(String pathname, String typeParser) throws ServiceException;
}
