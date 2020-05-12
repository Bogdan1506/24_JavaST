package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Profile;

import java.util.List;
import java.util.Map;

public interface ProfileService extends Service {
    int create(Profile profile) throws ServiceException;

    Profile create(Map<String, Object> parameters, Map<String, String> invalidParameters);

    List<Profile> findAll() throws ServiceException;

    Profile findById(int id) throws ServiceException;

    boolean delete(int id) throws ServiceException;

    void update(Profile profile) throws ServiceException;

    Profile findByUserId(int userId) throws ServiceException;
}
