package by.avdeev.task10final.gift.service;

import by.avdeev.task10final.gift.bean.Gift;
import by.avdeev.task10final.gift.bean.Sweets;
import by.avdeev.task10final.gift.bean.Tub;
import by.avdeev.task10final.gift.dao.GiftDAO;
import by.avdeev.task10final.gift.dao.exception.DAOException;
import by.avdeev.task10final.gift.dao.factory.DAOFactory;
import by.avdeev.task10final.gift.service.creator.GiftCreator;
import by.avdeev.task10final.gift.service.creator.SweetsCreator;
import by.avdeev.task10final.gift.service.creator.TubCreator;
import by.avdeev.task10final.gift.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class GiftServiceImpl implements GiftService {
    @Override
    public List<Gift> create(List<List<String>> strTubList, List<List<String>> strSweetList) throws ServiceException {
        List<Gift> giftList;
        GiftCreator giftCreator = new GiftCreator();
        SweetsCreator sweetsCreator = new SweetsCreator();
        TubCreator tubCreator = new TubCreator();
        List<Tub> tubs = tubCreator.createTub(strTubList);
        List<List<Sweets>> sweets = sweetsCreator.createSweets(strSweetList);
        giftList = giftCreator.createGift(tubs, sweets);
        Validator validator = new Validator();
        if (validator.checkWeight(giftList)) {
            return giftList;
        } else {
            throw new ServiceException("Incorrect weight!");
        }
    }

    @Override
    public void add(List<List<String>> list, String pathname) throws ServiceException {
        List<String> tempList = new ArrayList<>();
        for (List<String> str : list) {
            StringBuilder s = new StringBuilder();
            for (String s1 : str) {
                s.append(s1).append(" ");
            }
            tempList.add(s.toString());
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        GiftDAO dao = daoFactory.getGiftDAO();
        try {
            dao.add(tempList, pathname);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public List<Gift> findAll(String pathname) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        GiftDAO dao = daoFactory.getGiftDAO();
        List<List<String>> list;
        try {
            list = dao.findAll(pathname);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        Parser parser = new Parser();
        List<String> fileTubList = list.get(0);
        List<List<String>> parsedTubList = parser.parse(fileTubList);
        List<String> fileSweetsList = list.get(1);
        List<List<String>> parsedSweetsList = parser.parse(fileSweetsList);
        return create(parsedTubList, parsedSweetsList);
    }
}
