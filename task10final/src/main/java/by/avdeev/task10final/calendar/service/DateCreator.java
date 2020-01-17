package by.avdeev.task10final.calendar.service;

import by.avdeev.task10final.calendar.bean.Calendar;
import by.avdeev.task10final.calendar.bean.Status;

import java.util.ArrayList;
import java.util.List;

public class DateCreator {
    public List<Calendar.Date> createDates(List<List<String>> strDateList) {
        List<Calendar.Date> dates = new ArrayList<>();
        for (List<String> tempStrList : strDateList) {
            int dayOfMonth = Integer.parseInt(tempStrList.get(0));
            int monthOfYear = Integer.parseInt(tempStrList.get(1));
            int year = Integer.parseInt(tempStrList.get(2));
            Status status = Status.valueOf(tempStrList.get(3).toUpperCase());
            String name = tempStrList.get(4);
            Calendar.Date date = new Calendar().new Date(dayOfMonth, monthOfYear, year, status, name);
            dates.add(date);
        }
        return dates;
    }

    public List<Calendar.Date> createShortDates(List<List<String>> srtDateList) {
        List<Calendar.Date> dates = new ArrayList<>();
        for (List<String> tempStrList : srtDateList) {
            int dayOfMonth = Integer.parseInt(tempStrList.get(0));
            int monthOfYear = Integer.parseInt(tempStrList.get(1));
            int year = Integer.parseInt(tempStrList.get(2));
            Calendar.Date date = new Calendar().new Date(dayOfMonth, monthOfYear, year);
            dates.add(date);
        }
        return dates;
    }
}
