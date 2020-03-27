package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.User;

import java.util.List;

public interface UserService {
    void create(User user) throws ServiceException;

    List<User> findAll() throws ServiceException;

    User findById(int id) throws ServiceException;

    User findByLogin(String login) throws ServiceException;

    void delete(int id) throws ServiceException;

    void update(User user) throws ServiceException;

    boolean signIn(User user) throws ServiceException;

    boolean signUp(User user) throws ServiceException;
}
