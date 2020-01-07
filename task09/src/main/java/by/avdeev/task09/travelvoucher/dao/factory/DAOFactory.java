package by.avdeev.task09.travelvoucher.dao.factory;

import by.avdeev.task09.travelvoucher.dao.VoucherDAO;
import by.avdeev.task09.travelvoucher.dao.impl.VoucherDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory = new DAOFactory();

    private final VoucherDAO voucherDAO = new VoucherDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory;
    }

    public VoucherDAO getVoucherDAO() {
        return voucherDAO;
    }
}
