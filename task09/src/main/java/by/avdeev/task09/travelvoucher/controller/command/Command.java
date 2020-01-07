package by.avdeev.task09.travelvoucher.controller.command;

import by.avdeev.task09.travelvoucher.service.exception.ServiceException;
import by.avdeev.task09.travelvoucher.service.SortVoucherService;
import by.avdeev.task09.travelvoucher.service.VoucherService;
import by.avdeev.task09.travelvoucher.service.factory.ServiceFactory;
import by.avdeev.task09.travelvoucher.view.Printer;
import by.avdeev.task09.travelvoucher.view.Reader;

public interface Command {
    Reader reader = new Reader();
    Printer printer = new Printer();
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    VoucherService voucherService = serviceFactory.getVoucherService();
    SortVoucherService sortVoucherService = serviceFactory.getSortVoucherService();

    void execute() throws ServiceException;
}
