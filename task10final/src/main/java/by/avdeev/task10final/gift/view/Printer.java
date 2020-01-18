package by.avdeev.task10final.gift.view;

import by.avdeev.task10final.gift.bean.Gift;

import java.util.List;

import static java.lang.System.out;

public class Printer {
    public void printGiftList(List<Gift> giftList) {
        out.println("List of gifts: ");
        giftList.forEach(System.out::println);
    }

    public void printError() {
        out.println("The command is incorrect. Please, choose another");
    }
}
