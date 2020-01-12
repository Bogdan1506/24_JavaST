package by.avdeev.task10final.calendar.view;

import by.avdeev.task10final.calendar.bean.Calendar;
import by.avdeev.task10final.calendar.bean.Status;

import java.util.List;

import static java.lang.System.out;

public class Printer {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";

    public void showMonth(Calendar calendar) {
        List<Calendar.Date> dates = calendar.getDates();
        out.println(String.format("%10s", calendar.getMonth().toString()));
        for (int i = 0; i < 7; i++) {
            String day1 = "";
            String day2 = "";
            String day3 = "";
            String day4 = "";
            String day5 = "";
            try {
                day1 = dates.get(i).getStatus() == Status.WORKDAY ? String.valueOf(dates.get(i).getDay()) :
                        (ANSI_RED + dates.get(i).getDay());

                day2 = dates.get(i + 7).getStatus() == Status.WORKDAY ? String.valueOf(dates.get(i + 7).getDay()) :
                        (ANSI_RED + dates.get(i + 7).getDay());

                day3 = dates.get(i + 14).getStatus() == Status.WORKDAY ? String.valueOf(dates.get(i + 14).getDay()) :
                        (ANSI_RED + dates.get(i + 14).getDay());

                day4 = dates.get(i + 21).getStatus() == Status.WORKDAY ? String.valueOf(dates.get(i + 21).getDay()) :
                        (ANSI_RED + dates.get(i + 21).getDay());

                day5 = dates.get(i + 28).getStatus() == Status.WORKDAY ? String.valueOf(dates.get(i + 28).getDay()) :
                        (ANSI_RED + dates.get(i + 28).getDay());

            } catch (IndexOutOfBoundsException e) {

            }
            out.println(day1 + ANSI_RESET + " " + day2 + ANSI_RESET + " " + day3 + ANSI_RESET +
                    " " + day4 + ANSI_RESET + " " + day5 + ANSI_RESET);
        }
    }

    public void showDate(Calendar.Date date) {
        out.println(date);
    }
}
