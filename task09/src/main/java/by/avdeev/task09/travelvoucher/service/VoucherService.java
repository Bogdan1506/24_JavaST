package by.avdeev.task09.travelvoucher.service;

import by.avdeev.task09.travelvoucher.bean.TravelVoucher;
import by.avdeev.task09.travelvoucher.service.exception.ServiceException;

import java.util.List;
import java.util.Set;

public interface VoucherService {
    List<TravelVoucher> selectVoucher(TravelVoucher travelVoucher) throws ServiceException;

    void addVoucher(Set<TravelVoucher> travelVoucherSet) throws ServiceException;

    Set<TravelVoucher> findAll() throws ServiceException;
}
