package by.avdeev.task09.travelvoucher.controller.command.impl;

import by.avdeev.task09.travelvoucher.bean.TravelVoucher;
import by.avdeev.task09.travelvoucher.controller.command.Command;
import by.avdeev.task09.travelvoucher.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SortByNutrition implements Command {
    @Override
    public void execute() throws ServiceException {
        Set<TravelVoucher> travelVoucherSet = voucherService.findAll();
        List<TravelVoucher> travelVoucherList = new ArrayList<>(travelVoucherSet);
        sortVoucherService.sortByNutrition(travelVoucherList);
        printer.print(travelVoucherList);
    }
}
