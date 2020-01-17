package by.avdeev.task10final.textfile.dao;

import by.avdeev.task10final.textfile.bean.TextFile;
import by.avdeev.task10final.textfile.dao.exception.DAOException;

import java.io.File;

public interface TextFileDAO {
    void addText(TextFile textFile) throws DAOException;

    File findFile(String pathname);
}
