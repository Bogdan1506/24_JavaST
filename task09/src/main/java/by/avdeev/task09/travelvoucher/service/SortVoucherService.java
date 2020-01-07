package by.avdeev.task09.travelvoucher.service;

import by.avdeev.task09.travelvoucher.bean.TravelVoucher;

import java.util.List;

public interface SortVoucherService {
    void sortByType(List<TravelVoucher> travelVoucherList);

    void sortByPeriod(List<TravelVoucher> travelVoucherList);

    void sortByTransport(List<TravelVoucher> travelVoucherList);

    void sortByNutrition(List<TravelVoucher> travelVoucherList);
}
