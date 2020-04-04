package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Profile;

import java.util.List;

public interface ProfileService extends Service {
    int create(Profile profile) throws ServiceException;

    List<Profile> findAll() throws ServiceException;

    Profile findById(int id) throws ServiceException;

    boolean delete(int id) throws ServiceException;

    void update(Profile profile) throws ServiceException;

    Profile findByUserId(int userId) throws ServiceException;
}
