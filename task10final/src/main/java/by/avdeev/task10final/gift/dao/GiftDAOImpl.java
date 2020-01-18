package by.avdeev.task10final.gift.dao;

import by.avdeev.task10final.gift.dao.exception.DAOException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GiftDAOImpl implements GiftDAO {
    @Override
    public List<String> findAll(String pathname) throws DAOException {
        ReaderDAO readerDAO = new ReaderDAO();
        return readerDAO.readFile(pathname);
    }

    @Override
    public void add(List<String> strGiftList, String pathname) throws DAOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathname, true))) {
            for (String tempStrGift : strGiftList) {
                writer.write(tempStrGift);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new DAOException(e);
        }
    }
}
