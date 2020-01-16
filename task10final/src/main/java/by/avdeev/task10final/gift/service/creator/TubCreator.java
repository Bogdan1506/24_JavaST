package by.avdeev.task10final.gift.service.creator;

import by.avdeev.task10final.gift.bean.Tub;

import java.util.ArrayList;
import java.util.List;

public class TubCreator {
    public List<Tub> createTub(List<List<String>> strTub) {
        List<Tub> resList = new ArrayList<>();
        for (List<String> tubList : strTub) {
            Tub.Size size = Tub.Size.valueOf(tubList.get(0).toUpperCase());
            Tub.Form form = Tub.Form.valueOf(tubList.get(1).toUpperCase());
            Tub.Material material = Tub.Material.valueOf(tubList.get(2).toUpperCase());
            Tub.Color color = Tub.Color.valueOf(tubList.get(3).toUpperCase());
            resList.add(new Tub(size, form, material, color));
        }
        return resList;
    }
}
