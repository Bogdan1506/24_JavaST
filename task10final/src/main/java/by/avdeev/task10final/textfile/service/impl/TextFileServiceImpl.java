package by.avdeev.task10final.textfile.service.impl;

import by.avdeev.task10final.textfile.bean.TextFile;
import by.avdeev.task10final.textfile.dao.exception.DAOException;
import by.avdeev.task10final.textfile.service.TextFileService;
import by.avdeev.task10final.textfile.service.exception.ServiceException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Stream;

public class TextFileServiceImpl implements TextFileService {
    @Override
    public Stream<String> printConsole(TextFile textFile) throws ServiceException {
        Stream<String> stringStream = null;
        if (checkExtension(textFile)) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(textFile.getTextFile()));
                stringStream = reader.lines();
            } catch (FileNotFoundException e) {
                throw new ServiceException(e);
            }
        }
        return stringStream;
    }

    @Override
    public void addText(TextFile textFile) throws ServiceException {
        if (checkExtension(textFile)) {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            textFile.setText(text);
            try {
                dao.addText(textFile);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public void rename(TextFile textFile, String dest) throws ServiceException {
        if (checkExtension(textFile)) {
            textFile.rename(dest);
        }
    }

    @Override
    public void createFile(TextFile textFile) throws ServiceException {
        if (checkExtension(textFile)) {
            try {
                textFile.getTextFile().createNewFile();
            } catch (IOException e) {
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public void removeFile(TextFile textFile) throws ServiceException {
        if (checkExtension(textFile)) {
            dao.removeFile(textFile);
        }
    }

    private boolean checkExtension(TextFile textFile) throws ServiceException {
        if (!textFile.getName().endsWith(".txt")) {
            throw new ServiceException("Incorrect extension");
        }
        return true;
    }
}
