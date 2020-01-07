package by.avdeev.task09.travelvoucher.view;

import by.avdeev.task09.travelvoucher.bean.TravelVoucher;

import java.util.List;

import static java.lang.System.out;

public class Printer {
    public void print(List<TravelVoucher> list) {
        for (TravelVoucher travelVoucher : list) {
            out.println(travelVoucher);
        }
    }
}
