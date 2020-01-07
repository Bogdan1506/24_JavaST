package by.avdeev.task09.travelvoucher.view;

import by.avdeev.task09.travelvoucher.bean.Nutrition;
import by.avdeev.task09.travelvoucher.bean.Transport;
import by.avdeev.task09.travelvoucher.bean.TravelVoucher;
import by.avdeev.task09.travelvoucher.bean.Type;
import by.avdeev.task09.travelvoucher.controller.command.CommandName;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public String readClient() {
        out.println(Arrays.toString(CommandName.values()));
        out.print("Select command: ");
        return scanner.nextLine();
    }

    public TravelVoucher readVoucher() {
        out.print("Print type: ");
        String strType = scanner.nextLine();
        Type type;
        if (strType.equals("")) {
            type = null;
        } else {
            type = Type.valueOf(strType.toUpperCase());
        }
        out.print("Print transport: ");
        String strTransport = scanner.nextLine();
        Transport transport;
        if (strTransport.equals("")) {
            transport = null;
        } else {
            transport = Transport.valueOf(strTransport.toUpperCase());
        }
        out.print("Print nutrition: ");
        String strNutrition = scanner.nextLine();
        Nutrition nutrition;
        if (strNutrition.equals("")) {
            nutrition = null;
        } else {
            nutrition = Nutrition.valueOf(strNutrition.toUpperCase());
        }
        out.print("Print period in days: ");
        String strDays = scanner.nextLine();
        int days;
        if (strDays.equals("")) {
            days = 0;
        } else {
            days = Integer.parseInt(strDays.toUpperCase());
        }
        return new TravelVoucher(type, transport, nutrition, days);
    }
}
