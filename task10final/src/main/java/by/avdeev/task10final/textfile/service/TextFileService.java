package by.avdeev.task10final.textfile.service;

import by.avdeev.task10final.textfile.bean.TextFile;
import by.avdeev.task10final.textfile.service.exception.ServiceException;

public interface TextFileService {

    TextFile findTextFile(String pathname) throws ServiceException;

    void addText(String pathname, String text) throws ServiceException;

    boolean createTextFile(String pathname) throws ServiceException;

    boolean removeFile(String pathname) throws ServiceException;

    boolean rename(String pathname, String newName) throws ServiceException;
}
