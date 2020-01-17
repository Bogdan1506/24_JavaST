package by.avdeev.task10final.textfile.dao.impl;

import by.avdeev.task10final.textfile.bean.TextFile;
import by.avdeev.task10final.textfile.dao.TextFileDAO;
import by.avdeev.task10final.textfile.dao.exception.DAOException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileDAOImpl implements TextFileDAO {
    @Override
    public void addText(TextFile textFile) throws DAOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFile.getFile(), true))) {
            writer.write(textFile.getText());
            writer.newLine();
        } catch (IOException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public File findFile(String pathname) {
        return new File(pathname);
    }
}
