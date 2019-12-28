package by.avdeev.task08.service.impl;

import by.avdeev.task08.bean.Phone;
import by.avdeev.task08.dao.PhoneDAO;
import by.avdeev.task08.dao.exception.DAOException;
import by.avdeev.task08.dao.factory.DAOFactory;
import by.avdeev.task08.service.PhoneService;
import by.avdeev.task08.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhoneServiceImpl implements PhoneService {
    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final PhoneDAO dao = daoFactory.getPhoneDAO();

    @Override
    public List<Phone> findAll() throws ServiceException {
        List<Phone> phones = new ArrayList<>();
        List<String> list = null;
        try {
            list = dao.findAll();
        } catch (DAOException e) {
            throw new ServiceException();
        }
        for (String s : list) {
            String[] ar = s.split(" ");
            phones.add(parse(ar));
        }
        return phones;
    }

    @Override
    public void addPhone(List<Phone> phones) throws ServiceException {
        List<String> strPhones = new ArrayList<>();
        for (Phone phone : phones) {
            String formattedPhone = phone.getId() + " " + phone.getName() + " " + phone.getSurname() + " " + phone.getPatronymic()
                    + " " + phone.getAddress() + " " + phone.getCreditCard() + " " + phone.getCredit()
                    + " " + phone.getDebit() + " " + phone.getInsideLine() + " " + phone.getOutsideLine();
            strPhones.add(formattedPhone);
        }
        try {
            dao.addPhone(strPhones);
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }

    @Override
    public List<Phone> findByInsideLineLimit(int limit) throws ServiceException {
        List<Phone> phones = new ArrayList<>();
        List<String> list = null;
        try {
            list = dao.findAll();
        } catch (DAOException e) {
            throw new ServiceException();
        }
        for (String s : list) {
            String[] ar = s.split(" ");
            if (Integer.parseInt(ar[8]) > limit) {
                phones.add(parse(ar));
            }
        }
        return phones;
    }

    @Override
    public List<Phone> findByOutsideLine() throws ServiceException {
        List<Phone> phones = new ArrayList<>();
        List<String> list = null;
        try {
            list = dao.findAll();
        } catch (DAOException e) {
            throw new ServiceException();
        }
        for (String s : list) {
            String[] ar = s.split(" ");
            if (Integer.parseInt(ar[9]) > 0) {
                phones.add(parse(ar));
            }
        }
        return phones;
    }

    @Override
    public List<Phone> findSorted() throws ServiceException {
        List<Phone> list = null;
        try {
            list = findAll();
        } catch (ServiceException e) {
            throw new ServiceException();
        }
        Collections.sort(list);
        return list;
    }

    @Override
    public long countId() throws ServiceException {
        try {
            return dao.countLines().toArray().length;
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }
}
