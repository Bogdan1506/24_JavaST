package by.avdeev.task10final.textfile.view;

import by.avdeev.task10final.textfile.bean.Directory;
import by.avdeev.task10final.textfile.bean.TextFile;
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
        out.print("Select command: ");
        return scanner.nextLine();
    }

    public TextFile readFile() {
        out.print("Print directory: ");
        Directory directory = new Directory(scanner.nextLine());
        out.print("Print filename: ");
        String filename = scanner.nextLine();
        return new TextFile(directory, filename);
    }
}
