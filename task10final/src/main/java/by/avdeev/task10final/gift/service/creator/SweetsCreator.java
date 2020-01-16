package by.avdeev.task10final.gift.service.creator;

import by.avdeev.task10final.gift.bean.Sweets;

import java.util.ArrayList;
import java.util.List;

public class SweetsCreator {
    public List<List<Sweets>> createSweets(List<List<String>> strSweets) {
        List<List<Sweets>> sweets = new ArrayList<>();
        for (List<String> stringList : strSweets) {
            List<Sweets> tempList = new ArrayList<>();
            for (int i = 0; i < stringList.size(); i += 3) {
                String name = stringList.get(i);
                String firm = stringList.get(1 + i);
                double weight = Double.parseDouble(stringList.get(2 + i));
                tempList.add(new Sweets(name, firm, weight));
            }
            sweets.add(tempList);
        }
        return sweets;
    }
}
