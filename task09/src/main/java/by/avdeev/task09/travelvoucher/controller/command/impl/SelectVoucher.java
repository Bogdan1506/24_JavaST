package by.avdeev.task09.travelvoucher.controller.command.impl;

import by.avdeev.task09.travelvoucher.bean.TravelVoucher;
import by.avdeev.task09.travelvoucher.controller.command.Command;
import by.avdeev.task09.travelvoucher.service.exception.ServiceException;

import java.util.List;

public class SelectVoucher implements Command {
    @Override
    public void execute() throws ServiceException {
        TravelVoucher travelVoucher = reader.readVoucher();
        List<TravelVoucher> travelVoucherList = voucherService.selectVoucher(travelVoucher);
        printer.print(travelVoucherList);
    }
}
