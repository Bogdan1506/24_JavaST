package by.avdeev.task10final.gift.view;

import by.avdeev.task10final.gift.bean.Tub;
import by.avdeev.task10final.gift.controller.command.CommandName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public String readClient() {
        out.println(Arrays.toString(Arrays.stream(CommandName.values()).filter(a -> a != CommandName.WRONG_REQUEST).toArray()));
        out.print("Select command: ");
        return scanner.nextLine();
    }

    public String readPath() {
        out.print("Print path to the file: ");
        return scanner.nextLine();
    }

    public List<List<String>> readSweets() {
        List<List<String>> resList = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        while (true) {
            out.print("Print sweet's name: ");
            String name = scanner.nextLine();
            out.print("Print sweet's firm: ");
            String firm = scanner.nextLine();
            out.print("Print sweet's weight: ");
            String weight = scanner.nextLine();
            tempList.add(name);
            tempList.add(firm);
            tempList.add(weight);
            out.println("\nPrint 0 to stop");
            String stop = scanner.nextLine();
            if (stop.equals("0")) {
                break;
            }
        }
        resList.add(tempList);
        return resList;
    }

    public List<List<String>> readTub() {
        List<List<String>> resList = new ArrayList<>();
        out.println("Print size: " + Arrays.toString(Tub.Size.values()));
        String tub = scanner.nextLine();
        out.println("Print form: " + Arrays.toString(Tub.Form.values()));
        String form = scanner.nextLine();
        out.println("Print material: " + Arrays.toString(Tub.Material.values()));
        String material = scanner.nextLine();
        out.println("Print color: " + Arrays.toString(Tub.Color.values()));
        String color = scanner.nextLine();
        List<String> tempList = Arrays.asList(tub, form, material, color);
        resList.add(tempList);
        return resList;
    }
}
