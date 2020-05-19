package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService extends Service {
    int create(User user) throws ServiceException;

    int create(Map<String, Object> parameters, Map<String, String> invalidParameters) throws ServiceException;

    boolean changePassword(Map<String, Object> parameters, Map<String, String> invalidParameters, String login) throws ServiceException;

    List<User> findAll() throws ServiceException;

    int countAll() throws ServiceException;

    List<User> findAll(int begin, int end) throws ServiceException;

    User findById(int id) throws ServiceException;

    boolean delete(int id) throws ServiceException;

    boolean update(User user) throws ServiceException;

    User findByLogin(String login) throws ServiceException;

    void changeRole(Role role, int id) throws ServiceException;

    boolean userLogin(User user, String password) throws ServiceException;
}
