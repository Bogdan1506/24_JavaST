package by.avdeev.task08.view;

import by.avdeev.task08.bean.Phone;

import java.util.List;

public class Writer {
    public void showPhone(List<Phone> phones) {
        for (Phone phone : phones) {
            System.out.println(phone);
        }
    }
}
