package by.avdeev.task10final.calendar.view;

import by.avdeev.task10final.calendar.bean.Status;
import by.avdeev.task10final.calendar.controller.command.CommandName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public String readYear() {
        out.print("Print year: ");
        return scanner.nextLine();
    }

    public String readPath() {
        out.print("Print path: ");
        return scanner.nextLine();
    }

    public String readClient() {
        out.println(Arrays.toString(Arrays.stream(CommandName.values()).filter(a -> a != CommandName.WRONG_REQUEST).toArray()));
        out.print("Select command: ");
        return scanner.nextLine();
    }

    public List<String> readDates() {
        List<String> dates = new ArrayList<>();
        while (true) {
            StringBuilder res = new StringBuilder();
            out.print("Print day: ");
            String day = scanner.nextLine();
            res.append(day).append(" ");
            out.print("Print month: ");
            String month = scanner.nextLine();
            res.append(month).append(" ");
            out.print("Print year: ");
            String year = scanner.nextLine();
            res.append(year);
            dates.add(res.toString());
            out.println("Print 0 to stop. 1 - to continue");
            String stop = scanner.nextLine();
            if (stop.equals("0")) {
                break;
            }
        }
        return dates;
    }

    public List<String> readFullDates() {
        List<String> dates = new ArrayList<>();
        while (true) {
            StringBuilder res = new StringBuilder();
            out.print("Print day: ");
            String day = scanner.nextLine();
            res.append(day).append(" ");
            out.print("Print month: ");
            String month = scanner.nextLine();
            res.append(month).append(" ");
            out.print("Print year: ");
            String year = scanner.nextLine();
            res.append(year).append(" ");
            out.println(Arrays.toString(Arrays.stream(Status.values()).filter(a -> a != Status.WORKDAY).toArray()));
            out.print("Print status: ");
            String status = scanner.nextLine();
            res.append(status).append(" ");
            out.print("Print holiday's name: ");
            String name = scanner.nextLine();
            res.append(name);
            dates.add(res.toString());
            out.println("Print 0 to stop. 1 - to continue");
            String stop = scanner.nextLine();
            if (stop.equals("0")) {
                break;
            }
        }
        return dates;
    }
}
