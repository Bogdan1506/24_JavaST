package by.avdeev.task10final.calendar.dao.impl;

import by.avdeev.task10final.calendar.bean.Calendar;
import by.avdeev.task10final.calendar.dao.CalendarDAO;
import by.avdeev.task10final.calendar.dao.exception.DAOException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class CalendarDAOImpl implements CalendarDAO {
    private static String pathname;

    static {
        Scanner scanner = new Scanner(System.in);
        System.out.print("print path to file: ");
        pathname = scanner.nextLine();
    }

    @Override
    public File findFile() {
        return new File(pathname);
    }

    @Override
    public void addDay(Set<Calendar.Date> dates) throws DAOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(pathname), true))) {
            for(Calendar.Date date : dates) {
            writer.write(date.toString());
            writer.newLine();
            }
        } catch (IOException e) {
            throw new DAOException(e);
        }
    }
}
