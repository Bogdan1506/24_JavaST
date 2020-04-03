package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.entity.Profile;

import java.util.List;

public interface ProfileService {
    int create(Profile profile) throws ServiceException;

    List<Profile> findAll() throws ServiceException;

    Profile findById(int id) throws ServiceException;

    void delete(int id) throws ServiceException;

    void update(Profile profile) throws ServiceException;

    Profile findByUserId(int userId) throws ServiceException;
}
