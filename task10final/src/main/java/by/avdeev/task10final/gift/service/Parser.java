package by.avdeev.task10final.gift.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public List<List<String>> parse(List<String> stringList) {
        List<List<String>> parsedList = new ArrayList<>();
        for (String s : stringList) {
            List<String> tempList = new ArrayList<>();
            tempList.addAll(Arrays.asList(s.split(" ")));
            parsedList.add(tempList);
        }
        return parsedList;
    }
}
