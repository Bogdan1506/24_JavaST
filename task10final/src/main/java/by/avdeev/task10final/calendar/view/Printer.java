package by.avdeev.task10final.calendar.view;

import by.avdeev.task10final.calendar.bean.Calendar;
import by.avdeev.task10final.calendar.bean.Months;

import java.util.List;

import static java.lang.System.out;

public class Printer {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";


    public void showYear(Calendar calendar) {
        List<Calendar.Date> dates = calendar.getDates();
        int year = calendar.getYear();
        int[] days = {31, calendar.isLeap() ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (int z = 0; z < 12; ++z) {
            Months month = Months.values()[z];
            out.println(month);
            for (int i = 0; i < 7; ++i) {
                for (int k = 1 + i, j = 0; j < 5; k += 7, ++j) {
                    if (k > days[z]) {
                        break;
                    }
                    Calendar.Date date = new Calendar().new Date(k, month.getNum(), year);
                    if (dates.contains(date)) {
                        out.print(ANSI_RED + k + ANSI_RESET);
                    } else {
                        out.print(k);
                    }
                    out.print(" ");
                }
                out.println();
            }
        }
    }

    public void showDates(List<Calendar.Date> dates) {
        out.println("Dates: ");
        dates.forEach(System.out::println);
    }
}
