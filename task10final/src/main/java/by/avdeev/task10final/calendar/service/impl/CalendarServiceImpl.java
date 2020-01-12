package by.avdeev.task10final.calendar.service.impl;

import by.avdeev.task10final.calendar.bean.Calendar;
import by.avdeev.task10final.calendar.bean.Months;
import by.avdeev.task10final.calendar.bean.Status;
import by.avdeev.task10final.calendar.dao.exception.DAOException;
import by.avdeev.task10final.calendar.service.CalendarService;
import by.avdeev.task10final.calendar.service.exception.ServiceException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CalendarServiceImpl implements CalendarService {
    private void findStatus(Calendar.Date date) throws ServiceException {
        try {
            for (Calendar.Date fileDate : readFile(dao.findFile())) {
                if (date.equals(fileDate)) {
                    date.setStatus(fileDate.getStatus());
                    return;
                }
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Calendar createMonth(Calendar.Date date) throws ServiceException {
        Calendar calendar = new Calendar();
        calendar.setMonth(Months.values()[date.getMonth() - 1]);
        while (true) {
            try {
                findStatus(date);
            } catch (ServiceException e) {
                throw new ServiceException(e);
            }
            calendar.getDates().add(date);
            if (date.getMonth() != nextDay(date).getMonth()) {
                break;
            }
            date = nextDay(date);
        }
        return calendar;
    }

    @Override
    public Calendar.Date nextDay(Calendar.Date date) throws ServiceException {
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();
        boolean isLeap = date.isLeap();
        int[] days = {31, isLeap ? 29 : 28, 31, 30, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int daysInMonth = 0;
        try {
            daysInMonth = days[month - 1];
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (day <= 0 || month <= 0 || month > 12 || day > daysInMonth) {
            throw new ServiceException("Incorrect date");
        }
        if (day == 31 && month == 12) {
            year += 1;
        }
        if (day == daysInMonth) {
            day = 1;
            month = (month == 12) ? 1 : (month += 1);
        } else {
            day += 1;
        }
        Calendar.Date nextDate = new Calendar().new Date(day, month, year);
        findStatus(nextDate);
        return nextDate;
    }

    @Override
    public void today(Calendar.Date date) throws ServiceException {
        boolean isLeap = date.isLeap();
        int[] days = {31, isLeap ? 29 : 28, 31, 30, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int daysInMonth = 0;
        try {
            daysInMonth = days[date.getMonth() - 1];
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (date.getDay() <= 0 || date.getMonth() <= 0 || date.getMonth() > 12 || date.getDay() > daysInMonth) {
            throw new ServiceException("Incorrect date");
        }
        try {
            findStatus(date);
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
    }

    public Set<Calendar.Date> readFile(File file) throws ServiceException {
        Set<Calendar.Date> dates = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                String strDate = reader.readLine();
                String[] splitDate = strDate.split(" ");
                Calendar.Date date = new Calendar().new Date(
                        Integer.parseInt(splitDate[0]),
                        Integer.parseInt(splitDate[1]),
                        Integer.parseInt(splitDate[2]),
                        Status.valueOf(splitDate[3].toUpperCase()));
                dates.add(date);
            }
        } catch (FileNotFoundException e) {
            throw new ServiceException(e);
        } catch (IOException e) {
            throw new ServiceException(e);
        }
        return dates;
    }

    @Override
    public void addDay(Set<Calendar.Date> dates) throws ServiceException {
        try {
            dao.addDay(dates);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
