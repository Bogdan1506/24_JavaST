package by.avdeev.task08.service;

import by.avdeev.task08.bean.Phone;
import by.avdeev.task08.service.exception.ServiceException;

import java.util.List;

public interface PhoneService {
    void addPhone(List<Phone> phones) throws ServiceException;

    List<Phone> findAll() throws ServiceException;

    List<Phone> findByInsideLineLimit(int limit) throws ServiceException;

    List<Phone> findByOutsideLine() throws ServiceException;

    List<Phone> findSorted() throws ServiceException;

    long countId() throws ServiceException;

    default Phone parse(String[] phones) throws ServiceException {
        Phone.Builder phone = new Phone.Builder(phones[1], phones[2], phones[3]);
        phone.setAddress(phones[4]).setCreditCard(Long.parseLong(phones[5])).setDebit(Long.parseLong(phones[6])).
                setCredit(Long.parseLong(phones[7])).setInsideLine(Integer.parseInt(phones[8])).setOutsideLine(Integer.parseInt(phones[9])).setId(Long.parseLong(phones[0]));
        return phone.build();
    }
}
