package by.avdeev.task08.dao.impl;

import by.avdeev.task08.dao.PhoneDAO;
import by.avdeev.task08.dao.exception.DAOException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.lang.System.out;

public class PhoneDAOImpl implements PhoneDAO {
    private static String path;

    static {
        out.print("Укажите путь к файлу: ");
        Scanner scanner = new Scanner(System.in);
        path = scanner.nextLine();
    }

    @Override
    public void addPhone(List<String> phones) throws DAOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path, true)) {
            for (String phone : phones) {
                fileOutputStream.write(phone.getBytes());
                fileOutputStream.write(System.lineSeparator().getBytes());
                fileOutputStream.flush();
            }
        } catch (IOException e) {
            throw new DAOException();
        }
    }

    @Override
    public List<String> findAll() throws DAOException {
        List<String> phones = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(path);
             Scanner scannerFile = new Scanner(fileInputStream)) {
            while (scannerFile.hasNextLine()) {
                phones.add(scannerFile.nextLine());
            }
        } catch (IOException e) {
            throw new DAOException();
        }
        return phones;
    }

    public Stream<String> countLines() throws DAOException {
        Stream<String> streamLines = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            streamLines = reader.lines();
        } catch (IOException e) {
            throw new DAOException();
        }
        return streamLines;
    }
}
