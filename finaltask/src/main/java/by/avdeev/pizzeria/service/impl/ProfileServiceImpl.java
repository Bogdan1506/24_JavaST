package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.AbstractDAO;
import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.impl.ProfileDAOImpl;
import by.avdeev.pizzeria.entity.Profile;
import by.avdeev.pizzeria.service.ProfileService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.creator.Creator;
import by.avdeev.pizzeria.service.creator.ProfileCreator;
import by.avdeev.pizzeria.service.validator.Validator;
import by.avdeev.pizzeria.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ProfileServiceImpl extends StandardServiceImpl<Profile> implements ProfileService {
    private static Logger logger = LogManager.getLogger();

    /*@Override
    public int create(Profile profile) throws ServiceException {
        return 0;
    }*/

    @Override
    public Profile create(Map<String, Object> parameters, Map<String, String> invalidParameters) {
        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
        Validator profileValidator = validatorFactory.findValidator(type);
        if (profileValidator.validate(parameters, invalidParameters)) {
            Creator<Profile> creator = new ProfileCreator();
            return creator.create(parameters);
        }
        return null;
    }

    @Override
    public Profile findByUserId(int userId) throws ServiceException {
        AbstractDAO<Profile> abstractDAO = transaction.createDao(type);
        ProfileDAOImpl profileDAO1 = (ProfileDAOImpl) abstractDAO;
        Profile profile;
        try {
            profile = profileDAO1.findByUserId(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return profile;
    }
}
