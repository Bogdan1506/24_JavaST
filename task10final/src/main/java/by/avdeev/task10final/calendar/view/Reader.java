package by.avdeev.task10final.calendar.view;

import by.avdeev.task10final.calendar.bean.Calendar;
import by.avdeev.task10final.calendar.bean.Status;
import by.avdeev.task10final.calendar.controller.command.CommandName;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static java.lang.System.out;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public String readClient() {
        out.println(Arrays.toString(Arrays.stream(CommandName.values()).filter(a -> a != CommandName.WRONG_REQUEST).toArray()));
        out.print("Select command: ");
        return scanner.nextLine();
    }

    public Calendar.Date readDate() {
        out.print("Print day: ");
        int day = scanner.nextInt();
        out.print("Print month: ");
        int month = scanner.nextInt();
        out.print("Print year: ");
        int year = scanner.nextInt();
        return new Calendar().new Date(day, month, year);
    }

    public Calendar.Date readFullDate() {
        Calendar.Date date = readDate();
        Scanner scanner = new Scanner(System.in);
        out.println(Arrays.toString(Arrays.stream(Status.values()).filter(a -> a != Status.WORKDAY).toArray()));
        out.print("Print status: ");
        date.setStatus(Status.valueOf(scanner.nextLine().toUpperCase()));
        return date;
    }

    public Set<Calendar.Date> readDateList() {
        Set<Calendar.Date> dates = new HashSet<>();
        while (true) {
            dates.add(readFullDate());
            scanner.nextLine();
            out.println("\npress 0 to stop\n1 to continue");
            String stop = scanner.nextLine();
            if (stop.equals("0")) {
                break;
            }
        }
        return dates;
    }
}
