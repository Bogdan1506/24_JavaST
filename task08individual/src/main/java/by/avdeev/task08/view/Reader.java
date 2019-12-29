package by.avdeev.task08.view;

import by.avdeev.task08.bean.Phone;
import by.avdeev.task08.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public List<Phone> readPhone() throws ServiceException {
        List<Phone> phones = new ArrayList<>();
        while (true) {
            out.println("print stop to exit");
            out.print("Введите имя: ");
            String name = scanner.nextLine();
            if (name.equals("stop")) {
                break;
            }
            if (name.equals("")) {
                throw new NullPointerException();
            }
            out.print("Введите фамилию: ");
            String surname = scanner.nextLine();
            if (surname.equals("")) {
                throw new NullPointerException();
            }
            out.print("Введите отчество: ");
            String patronymic = scanner.nextLine();
            if (patronymic.equals("")) {
                throw new NullPointerException();
            }
            Phone.Builder phone = new Phone.Builder(name, surname, patronymic);
            out.print("Введите адрес: ");
            String address = scanner.nextLine();
            if (address.equals("")) {
                address = "";
            }
            phone.setAddress(address);
            out.print("Введите номер кредитной карточки: ");
            String temp = scanner.nextLine();
            if (!temp.equals("")) {
                phone.setCreditCard(Long.parseLong(temp));
            }
            out.print("Введите дебет: ");
            temp = scanner.nextLine();
            if (!temp.equals("")) {
                phone.setDebit(Long.parseLong(temp));
            }
            out.print("Введите кредит: ");
            temp = scanner.nextLine();
            if (!temp.equals("")) {
                phone.setCredit(Long.parseLong(temp));
            }
            out.print("Введите время городских разговоров: ");
            temp = scanner.nextLine();
            if (!temp.equals("")) {
                phone.setInsideLine(Integer.parseInt(temp));
            }
            out.print("Введите время междугородных разговоров: ");
            temp = scanner.nextLine();
            if (!temp.equals("")) {
                phone.setOutsideLine(Integer.parseInt(temp));
            }
            phones.add(phone.build());
        }
        return phones;
    }

    public int readLimit() {
        out.print("Введите лимит городских разговоров: ");
        return scanner.nextInt();
    }
}
