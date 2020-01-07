package by.avdeev.task09.travelvoucher.dao;

import by.avdeev.task09.travelvoucher.bean.TravelVoucher;
import by.avdeev.task09.travelvoucher.dao.exception.DAOException;

import java.io.IOException;
import java.util.Set;

public interface VoucherDAO {
    void addVoucher(Set<TravelVoucher> list) throws IOException;

    Set<TravelVoucher> readVoucher() throws IOException, ClassNotFoundException, DAOException;

    boolean check() throws IOException, DAOException;
}
