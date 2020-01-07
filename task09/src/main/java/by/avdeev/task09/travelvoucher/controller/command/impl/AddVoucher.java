package by.avdeev.task09.travelvoucher.controller.command.impl;

import by.avdeev.task09.travelvoucher.bean.TravelVoucher;
import by.avdeev.task09.travelvoucher.controller.command.Command;
import by.avdeev.task09.travelvoucher.service.exception.ServiceException;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AddVoucher implements Command {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() throws ServiceException {
        Set<TravelVoucher> travelVoucherSet = new HashSet<>();
        while (true) {
            TravelVoucher travelVoucher = reader.readVoucher();
            travelVoucherSet.add(travelVoucher);
            System.out.println("press 0 to stop\n1 to continue");
            String stop = scanner.nextLine();
            if (stop.equals("0")) {
                break;
            }
        }
        voucherService.addVoucher(travelVoucherSet);
    }
}
