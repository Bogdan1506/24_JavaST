package by.avdeev.task10final.gift.service;

import by.avdeev.task10final.gift.bean.Gift;
import by.avdeev.task10final.gift.dao.GiftDAO;
import by.avdeev.task10final.gift.dao.exception.DAOException;
import by.avdeev.task10final.gift.dao.factory.DAOFactory;
import by.avdeev.task10final.gift.service.exception.ServiceException;

import java.util.List;

public class GiftServiceImpl implements GiftService {
    @Override
    public List<Gift> create(List<String> strGiftList) throws ServiceException {
        List<Gift> giftList;

        Parser parser = new Parser();
        List<List<String>> strParsedList = parser.parse(strGiftList);

        GiftCreator creator = new GiftCreator();
        giftList = creator.createGift(strParsedList);

        Validator validator = new Validator();
        if (validator.checkWeight(giftList)) {
            return giftList;

        } else {
            throw new ServiceException("Incorrect weight");
        }
    }

    @Override
    public void add(List<String> strGiftList, String pathname) throws ServiceException {
        List<Gift> giftList = create(strGiftList);
        DAOFactory factory = DAOFactory.getInstance();
        GiftDAO dao = factory.getGiftDAO();
        try {
            dao.add(strGiftList, pathname);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Gift> findAll(String pathname) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        GiftDAO dao = factory.getGiftDAO();
        List<String> strGiftList;
        try {
            strGiftList = dao.findAll(pathname);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return create(strGiftList);
    }
}
