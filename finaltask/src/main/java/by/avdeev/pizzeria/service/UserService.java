package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;

import java.util.List;

public interface UserService extends Service {
    int create(User user) throws ServiceException;

    List<User> findAll() throws ServiceException;

    int countAll() throws ServiceException;

    List<User> findAll(int begin, int end) throws ServiceException;

    User findById(int id) throws ServiceException;

    boolean delete(int id) throws ServiceException;

    void update(User user) throws ServiceException;

    User findByLogin(String login) throws ServiceException;

    void changeRole(Role role, int id) throws ServiceException;
}
