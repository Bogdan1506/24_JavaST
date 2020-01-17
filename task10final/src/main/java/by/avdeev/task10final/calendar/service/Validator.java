package by.avdeev.task10final.calendar.service;

import by.avdeev.task10final.calendar.bean.Calendar;

import java.util.List;

public class Validator {
    public List<Calendar.Date> checkYear(int year, List<Calendar.Date> dates) {
        dates.removeIf(date -> date.getYear() != year);
        return dates;
    }

    public boolean checkDates(List<Calendar.Date> dates) {
        Calendar calendar = new Calendar();
        calendar.setYear(dates.get(0).getYear());
        int[] days = {31, calendar.isLeap() ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (Calendar.Date date : dates) {
            if (date.getYear() < 1 || date.getMonth() < 0 || date.getMonth() > 12 ||
                    date.getDay() > days[date.getMonth()] || date.getDay() < 1) {
                return false;
            }
        }
        return true;
    }
}
