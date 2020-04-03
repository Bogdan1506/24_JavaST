package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.User;

import java.util.List;

public interface UserService {
    int create(User user) throws ServiceException;

    List<User> findAll() throws ServiceException;

    User findById(int id) throws ServiceException;

    void delete(int id) throws ServiceException;

    void update(User user) throws ServiceException;

    User findByLogin(String login) throws ServiceException;
}
