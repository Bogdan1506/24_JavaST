package by.avdeev.task10final.gift.service.creator;

import by.avdeev.task10final.gift.bean.Gift;
import by.avdeev.task10final.gift.bean.Sweets;
import by.avdeev.task10final.gift.bean.Tub;

import java.util.ArrayList;
import java.util.List;

public class GiftCreator {
    public List<Gift> createGift(List<Tub> tubs, List<List<Sweets>> sweets) {
        List<Gift> giftList = new ArrayList<>();
        for (int i = 0; i < tubs.size(); ++i) {
            giftList.add(new Gift(tubs.get(i), sweets.get(i)));
        }
        return giftList;
    }
}
