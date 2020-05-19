package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.ProfileDAOImpl;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.creator.Creator;
import by.avdeev.pizzeria.service.creator.CreatorFactory;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.ValidatorFactory;

import java.util.Map;

public class ProfileServiceImpl
        extends StandardServiceImpl<Profile> implements ProfileService {
    /**
     * @param parameters        Gotten inputs from user.
     * @param invalidParameters List of incorrect inputs from user.
     * @return Id of the pushed ${@link Profile} bean.
     * @throws ServiceException If there was an exception in DAO layer.
     */
    @Override
    public int create(final Map<String, Object> parameters,
                      final Map<String, String> invalidParameters)
            throws ServiceException {
        AbstractDAO<Profile> dao = getTransaction().createDao(getType());
        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
        Validator profileValidator = validatorFactory.findValidator(getType());
        if (profileValidator.validate(parameters, invalidParameters)) {
            CreatorFactory creatorFactory = CreatorFactory.getInstance();
            @SuppressWarnings("unchecked")
            Creator<Profile> creator = creatorFactory.findCreator(getType());
            Profile profile = creator.create(parameters);
            try {
                return dao.create(profile);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return -1;
    }

    /**
     * @param parameters        Gotten data from user.
     * @param invalidParameters List of incorrect user inputs.
     * @param id                Id of the ${@link Profile} bean.
     * @return true if it was updated else false.
     * @throws ServiceException it there was an exception in DAO layer.
     */
    @Override
    public boolean update(final Map<String, Object> parameters,
                          final Map<String, String> invalidParameters,
                          final int id)
            throws ServiceException {
        AbstractDAO<Profile> dao = getTransaction().createDao(getType());
        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
        Validator profileValidator = validatorFactory.findValidator(getType());
        if (profileValidator.validate(parameters, invalidParameters)) {
            CreatorFactory creatorFactory = CreatorFactory.getInstance();
            @SuppressWarnings("unchecked")
            Creator<Profile> creator = creatorFactory.findCreator(getType());
            Profile profile = creator.create(parameters);
            try {
                profile.setId(id);
                dao.update(profile);
                return true;
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    /**
     * @param login Login of user.
     * @return Bean ${@link Profile}.
     * @throws ServiceException it there was an exception in DAO layer.
     */
    @Override
    public Profile findByUserLogin(final String login) throws ServiceException {
        AbstractDAO<Profile> abstractDAO = getTransaction().createDao(getType());
        ProfileDAOImpl dao = (ProfileDAOImpl) abstractDAO;
        try {
            return dao.findByUserLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
