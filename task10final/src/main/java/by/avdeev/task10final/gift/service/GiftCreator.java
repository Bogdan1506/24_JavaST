package by.avdeev.task10final.gift.service;

import by.avdeev.task10final.gift.bean.Gift;
import by.avdeev.task10final.gift.bean.Sweets;
import by.avdeev.task10final.gift.bean.Tub;
import by.avdeev.task10final.gift.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class GiftCreator {
    public List<Gift> createGift(List<List<String>> strGiftList) throws ServiceException {
        List<Gift> giftList = new ArrayList<>();
        for (List<String> tempGiftList : strGiftList) {
            try {
                Tub.Size size = Tub.Size.valueOf(tempGiftList.get(0).toUpperCase());
                Tub.Form form = Tub.Form.valueOf(tempGiftList.get(1).toUpperCase());
                Tub.Material material = Tub.Material.valueOf(tempGiftList.get(2).toUpperCase());
                Tub.Color color = Tub.Color.valueOf(tempGiftList.get(3).toUpperCase());
                Tub tub = new Tub(size, form, material, color);
                List<Sweets> sweetsList = new ArrayList<>();
                for (int i = 4; i < tempGiftList.size(); i += 3) {
                    Sweets sweets = new Sweets(
                            tempGiftList.get(i),
                            tempGiftList.get(i + 1),
                            Double.parseDouble(tempGiftList.get(i + 2)));
                    sweetsList.add(sweets);
                }
                Gift gift = new Gift(tub, sweetsList);
                giftList.add(gift);
            } catch (Exception e) {
                throw new ServiceException(e);
            }
        }
        return giftList;
    }
}
