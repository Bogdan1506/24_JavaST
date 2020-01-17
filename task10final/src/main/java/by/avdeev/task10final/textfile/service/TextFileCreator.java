package by.avdeev.task10final.textfile.service;

import by.avdeev.task10final.textfile.bean.Directory;
import by.avdeev.task10final.textfile.bean.TextFile;
import by.avdeev.task10final.textfile.service.exception.ServiceException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextFileCreator {
    public TextFile createTextFile(String pathname) {
        int index = pathname.lastIndexOf('\\');
        String parent = pathname.substring(0, index);
        String child = pathname.substring(index + 1);
        Directory directory = new Directory(parent);
        return new TextFile(directory, child);
    }

    public TextFile createTextFile(File file) throws ServiceException {
        String parent = file.getParent();
        Directory directory = new Directory(parent);
        String child = file.getName();
        TextFile textFile = new TextFile(directory, child);
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                text.append(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            throw new ServiceException(e);
        } catch (IOException e) {
            throw new ServiceException(e);
        }
        textFile.setText(text.toString());
        return textFile;
    }
}
