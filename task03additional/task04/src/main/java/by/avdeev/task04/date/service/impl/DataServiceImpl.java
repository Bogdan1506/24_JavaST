package by.avdeev.task04.date.service.impl;

import by.avdeev.task04.date.bean.Date;
import by.avdeev.task04.date.service.DateService;
import by.avdeev.task04.date.service.exception.ServiceException;

public class DataServiceImpl implements DateService {
    @Override
    public Date nextDay(Date date) throws ServiceException {
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();
        boolean isLeap = date.isLeap();
        int[] days = {31, isLeap ? 29 : 28, 31, 30, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int daysInMonth;
        try {
            daysInMonth = days[month - 1];
        } catch (Exception ex) {
            throw new ServiceException();
        }
        if (day <= 0 || month <= 0 || month > 12 || day > daysInMonth) {
            throw new ServiceException();
        }
        if (day == 31 && month == 12) {
            year += 1;
        }
        if(day == daysInMonth) {
            day = 1;
            month = (month == 12) ? 1 : (month += 1);
        } else {
            day += 1;
        }
        return new Date(day, month, year);
    }
}
