package by.avdeev.task10final.textfile.service;

import by.avdeev.task10final.textfile.bean.TextFile;
import by.avdeev.task10final.textfile.dao.TextFileDAO;
import by.avdeev.task10final.textfile.dao.factory.DAOFactory;
import by.avdeev.task10final.textfile.service.exception.ServiceException;

import java.util.stream.Stream;

public interface TextFileService {
    DAOFactory factory = DAOFactory.getInstance();
    TextFileDAO dao = factory.getTextFileDAO();

    void createFile(TextFile textFile) throws ServiceException;

    void rename(TextFile textFile, String dest);

    Stream<String> printConsole(TextFile textFile) throws ServiceException;

    void addText(TextFile textFile) throws ServiceException;

    void removeFile(TextFile textFile);
}
