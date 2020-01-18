package by.avdeev.task10final.textfile.view;

import by.avdeev.task10final.textfile.controller.command.CommandName;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public String readFileName() {
        out.print("Print new filename: ");
        return scanner.nextLine();
    }

    public String readClient() {
        out.println(Arrays.toString(Arrays.stream(CommandName.values()).filter(a -> a != CommandName.WRONG_REQUEST).toArray()));
        out.println("CREATE - CREATE NEW FILE");
        out.println("RENAME - RENAME FILE");
        out.println("PRINT - PRINT TEXT FROM THE FILE");
        out.println("ADD_TEXT - ADD SOME TEXT TO THE FILE (UPDATE)");
        out.println("REMOVE - DELETE FILE");
        out.print("Select command: ");
        return scanner.nextLine();
    }

    public String readPathname() {
        out.print("Print path to the file: ");
        return scanner.nextLine();
    }

    public String readText() {
        out.println("Print text: ");
        return scanner.nextLine();
    }
}
