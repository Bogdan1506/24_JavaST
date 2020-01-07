package by.avdeev.task09.travelvoucher.service.impl;

import by.avdeev.task09.travelvoucher.bean.TravelVoucher;
import by.avdeev.task09.travelvoucher.dao.VoucherDAO;
import by.avdeev.task09.travelvoucher.dao.exception.DAOException;
import by.avdeev.task09.travelvoucher.dao.factory.DAOFactory;
import by.avdeev.task09.travelvoucher.dao.impl.VoucherDAOImpl;
import by.avdeev.task09.travelvoucher.service.VoucherService;
import by.avdeev.task09.travelvoucher.service.exception.ServiceException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VoucherServiceImpl implements VoucherService {
    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final VoucherDAO voucherDAO = daoFactory.getVoucherDAO();

    @Override
    public List<TravelVoucher> selectVoucher(TravelVoucher travelVoucher) throws ServiceException {
        Set<TravelVoucher> travelVoucherListSet = null;
        try {
            travelVoucherListSet = voucherDAO.readVoucher();
        } catch (IOException | ClassNotFoundException | DAOException e) {
            throw new ServiceException(e);
        }
        List<TravelVoucher> travelVoucherList = new ArrayList<>(travelVoucherListSet);
        if (travelVoucher.getType() == null || travelVoucher.getTransport() == null ||
                travelVoucher.getNutrition() == null || travelVoucher.getPeriod() == 0) {
            if (travelVoucher.getType() != null) {
                for (int i = 0; i < travelVoucherList.size(); ++i) {
                    if (travelVoucherList.get(i).getType() != travelVoucher.getType()) {
                        travelVoucherList.remove(i);
                    }
                }
            }
            if (travelVoucher.getTransport() != null) {
                for (int i = 0; i < travelVoucherList.size(); ++i) {
                    if (travelVoucherList.get(i).getTransport() != travelVoucher.getTransport()) {
                        travelVoucherList.remove(i);
                        --i;
                    }
                }
            }
            if (travelVoucher.getNutrition() != null) {
                for (int i = 0; i < travelVoucherList.size(); ++i) {
                    if (travelVoucherList.get(i).getNutrition() != travelVoucher.getNutrition()) {
                        travelVoucherList.remove(i);
                        --i;
                    }
                }
            }
            if (travelVoucher.getPeriod() != 0) {
                for (int i = 0; i < travelVoucherList.size(); ++i) {
                    if (travelVoucherList.get(i).getPeriod() != travelVoucher.getPeriod()) {
                        travelVoucherList.remove(i);
                        --i;
                    }
                }
            }
        }
        return travelVoucherList;
    }

    @Override
    public void addVoucher(Set<TravelVoucher> travelVoucherSet) throws ServiceException {
        Set<TravelVoucher> travelVoucherSetDAO;
        try {
            if (voucherDAO.check()) {
                travelVoucherSetDAO = new HashSet<>(voucherDAO.readVoucher());
                travelVoucherSetDAO.addAll(travelVoucherSet);
                voucherDAO.addVoucher(travelVoucherSetDAO);
            }
        } catch (IOException | DAOException | ClassNotFoundException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Set<TravelVoucher> findAll() throws ServiceException {
        VoucherDAO dao = new VoucherDAOImpl();
        try {
            return dao.readVoucher();
        } catch (IOException | ClassNotFoundException | DAOException e) {
            throw new ServiceException(e);
        }
    }
}
