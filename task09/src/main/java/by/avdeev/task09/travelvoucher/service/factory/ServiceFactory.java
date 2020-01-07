package by.avdeev.task09.travelvoucher.service.factory;

import by.avdeev.task09.travelvoucher.service.SortVoucherService;
import by.avdeev.task09.travelvoucher.service.VoucherService;
import by.avdeev.task09.travelvoucher.service.impl.SortVoucherServiceImpl;
import by.avdeev.task09.travelvoucher.service.impl.VoucherServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory = new ServiceFactory();

    private final VoucherService voucherService = new VoucherServiceImpl();
    private final SortVoucherService sortVoucherService = new SortVoucherServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory;
    }

    public VoucherService getVoucherService() {
        return voucherService;
    }

    public SortVoucherService getSortVoucherService() {
        return sortVoucherService;
    }
}
