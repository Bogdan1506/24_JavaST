package by.avdeev.task11.view;

import by.avdeev.task11.controller.command.CommandName;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public String readPathname() {
        out.print("Print pathname: ");
        return scanner.nextLine();
    }

    public String readCommand() {
        out.println(Arrays.toString(Arrays.stream(CommandName.values()).filter(a -> a != CommandName.WRONG_REQUEST).toArray()));
        out.print("Print command: ");
        return scanner.nextLine();
    }

    public String readKey() {
        out.print("Print repository's key: ");
        return scanner.nextLine();
    }

    public String readContent() {
        out.print("Print content: ");
        return scanner.nextLine();
    }
}
