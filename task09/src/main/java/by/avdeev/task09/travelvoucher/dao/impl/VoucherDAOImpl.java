package by.avdeev.task09.travelvoucher.dao.impl;

import by.avdeev.task09.travelvoucher.bean.TravelVoucher;
import by.avdeev.task09.travelvoucher.dao.VoucherDAO;
import by.avdeev.task09.travelvoucher.dao.exception.DAOException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Set;

public class VoucherDAOImpl implements VoucherDAO {
    private static String path;

    static {
        System.out.print("Укажите путь к файлу: ");
        Scanner scanner = new Scanner(System.in);
        path = scanner.nextLine();
    }

    @Override
    public void addVoucher(Set<TravelVoucher> travelVoucherSet) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
        objectOutputStream.writeObject(travelVoucherSet);
        objectOutputStream.close();
    }

    @Override
    public Set<TravelVoucher> readVoucher() throws IOException, ClassNotFoundException, DAOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        Set<TravelVoucher> travelVoucherSet;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            travelVoucherSet = (Set<TravelVoucher>) objectInputStream.readObject();
        } catch (ClassCastException ex) {
            throw new DAOException(ex);
        }

        return travelVoucherSet;
    }

    public boolean check() throws DAOException {
        try (FileInputStream fileInputStream = new FileInputStream(path);) {
            return fileInputStream.available() > 0;
        } catch (IOException e) {
            throw new DAOException(e);
        }
    }
}

