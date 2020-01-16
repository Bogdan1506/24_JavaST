package by.avdeev.task10final.gift.service;

import by.avdeev.task10final.gift.bean.Gift;
import by.avdeev.task10final.gift.bean.Sweets;

import java.util.List;

public class Validator {
    public boolean checkWeight(List<Gift> giftList) {
        for (Gift gift : giftList) {
            List<Sweets> sweets = gift.getSweetsList();
            double sumWeight = 0;
            for (Sweets sweet : sweets) {
                sumWeight += sweet.getWeight();
            }
            if (gift.getTub().getSize().getCapacity() < sumWeight) {
                return false;
            }
        }
        return true;
    }
}
