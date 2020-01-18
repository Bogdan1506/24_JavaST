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
        out.println("CREATE - CREATE GIFT AND GET RESULT");
        out.println("ADD - ADD NEW GIFT TO THE FILE");
        out.println("FIND - SEEK GIFTS IN THE FILE");
        out.print("Select command: ");
        return scanner.nextLine();
    }

    public String readPath() {
        out.print("Print path to the file: ");
        return scanner.nextLine();
    }

    public List<String> readGift() {
        List<String> gifts = new ArrayList<>();
        while (true) {
            StringBuilder res = new StringBuilder();
            out.print("Print size " + Arrays.toString(Tub.Size.values()) + " : ");
            String size = scanner.nextLine();
            res.append(size).append(" ");
            out.print("Print form " + Arrays.toString(Tub.Form.values()) + " : ");
            String form = scanner.nextLine();
            res.append(form).append(" ");
            out.print("Print material " + Arrays.toString(Tub.Material.values()) + " : ");
            String material = scanner.nextLine();
            res.append(material).append(" ");
            out.print("Print color " + Arrays.toString(Tub.Color.values()) + " : ");
            String color = scanner.nextLine();
            res.append(color).append(" ");
            while (true) {
                out.print("Print sweet's name: ");
                String name = scanner.nextLine();
                res.append(name).append(" ");
                out.print("Print sweet's firm: ");
                String firm = scanner.nextLine();
                res.append(firm).append(" ");
                out.print("Print sweet's weight: ");
                String weight = scanner.nextLine();
                res.append(weight).append(" ");
                out.println("Print 0 to stop choosing sweets. 1 - to continue");
                String stop = scanner.nextLine();
                if (stop.equals("0")) {
                    break;
                }
            }
            gifts.add(res.toString());
            out.println("Print 0 to stop creating gifts. 1 - to continue");
            String stop = scanner.nextLine();
            if (stop.equals("0")) {
                break;
            }
        }
        return gifts;
    }
}
