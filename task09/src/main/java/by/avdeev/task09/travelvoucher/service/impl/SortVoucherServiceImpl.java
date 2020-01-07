package by.avdeev.task09.travelvoucher.service.impl;

import by.avdeev.task09.travelvoucher.bean.TravelVoucher;
import by.avdeev.task09.travelvoucher.service.SortVoucherService;

import java.util.Comparator;
import java.util.List;

public class SortVoucherServiceImpl implements SortVoucherService {
    @Override
    public void sortByType(List<TravelVoucher> travelVoucherList) {
        class SortByType implements Comparator<TravelVoucher> {
            @Override
            public int compare(TravelVoucher o1, TravelVoucher o2) {
                return o1.getType().toString().charAt(0) - o2.getType().toString().charAt(0);
            }
        }
        travelVoucherList.sort(new SortByType());
    }

    @Override
    public void sortByPeriod(List<TravelVoucher> travelVoucherList) {
        class SortByPeriod implements Comparator<TravelVoucher> {
            @Override
            public int compare(TravelVoucher o1, TravelVoucher o2) {
                return o1.getPeriod() - o2.getPeriod();
            }
        }
        travelVoucherList.sort(new SortByPeriod());
    }

    @Override
    public void sortByTransport(List<TravelVoucher> travelVoucherList) {
        class SortByTransport implements Comparator<TravelVoucher> {
            @Override
            public int compare(TravelVoucher o1, TravelVoucher o2) {
                return o1.getTransport().toString().charAt(0) - o2.getTransport().toString().charAt(0);
            }
        }
        travelVoucherList.sort(new SortByTransport());
    }

    @Override
    public void sortByNutrition(List<TravelVoucher> travelVoucherList) {
        class SortByNutrition implements Comparator<TravelVoucher> {
            @Override
            public int compare(TravelVoucher o1, TravelVoucher o2) {
                return o1.getNutrition().toString().charAt(0) - o2.getNutrition().toString().charAt(0);
            }
        }
        travelVoucherList.sort(new SortByNutrition());
    }
}


