package by.avdeev.task10final.gift.view;

import by.avdeev.task10final.gift.bean.Gift;

import java.util.List;

import static java.lang.System.out;

public class Printer {
    public void printGiftList(List<Gift> giftList) {
        out.println("List of gifts: ");
        giftList.stream().forEach(System.out::println);
    }
}
