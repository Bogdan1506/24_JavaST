package by.avdeev.task10final.textfile.service.impl;

import by.avdeev.task10final.textfile.bean.Directory;
import by.avdeev.task10final.textfile.bean.TextFile;
import by.avdeev.task10final.textfile.dao.TextFileDAO;
import by.avdeev.task10final.textfile.dao.exception.DAOException;
import by.avdeev.task10final.textfile.dao.factory.DAOFactory;
import by.avdeev.task10final.textfile.service.TextFileCreator;
import by.avdeev.task10final.textfile.service.TextFileService;
import by.avdeev.task10final.textfile.service.Validator;
import by.avdeev.task10final.textfile.service.exception.ServiceException;

import java.io.File;
import java.io.IOException;

public class TextFileServiceImpl implements TextFileService {

    @Override
    public TextFile findTextFile(String pathname) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        TextFileDAO dao = factory.getTextFileDAO();
        File file = dao.findFile(pathname);
        TextFileCreator creator = new TextFileCreator();
        TextFile textFile = creator.createTextFile(file);
        Validator validator = new Validator();
        if (validator.checkExtension(textFile)) {
            return textFile;
        } else {
            throw new ServiceException("Incorrect extension");
        }
    }

    @Override
    public void addText(String pathname, String text) throws ServiceException {
        TextFile textFile = findTextFile(pathname);
        textFile.setText(text);
        DAOFactory factory = DAOFactory.getInstance();
        TextFileDAO dao = factory.getTextFileDAO();
        try {
            dao.addText(textFile);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean rename(String pathname, String newName) throws ServiceException {
        TextFile textFile = findTextFile(pathname);
        Directory parent = textFile.getParent();
        File file = new File(parent.toString(), newName + ".txt");
        return textFile.getFile().renameTo(file);
    }

    public boolean createTextFile(String pathname) throws ServiceException {
        TextFileCreator creator = new TextFileCreator();
        TextFile textFile = creator.createTextFile(pathname);
        Validator validator = new Validator();
        if (validator.checkExtension(textFile)) {
            try {
                return textFile.getFile().createNewFile();
            } catch (IOException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException("Incorrect extension");
        }
    }

    @Override
    public boolean removeFile(String pathname) throws ServiceException {
        TextFile textFile = findTextFile(pathname);
        return textFile.getFile().delete();
    }
}
