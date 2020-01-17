package by.avdeev.task10final.calendar.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public List<List<String>> parseDate(List<String> strDates) {
        List<List<String>> parsedDates = new ArrayList<>();
        for (String tempDate : strDates) {
            String[] splitDates = tempDate.split(" ");
            List<String> tempList = Arrays.asList(splitDates);
            parsedDates.add(tempList);
        }
        return parsedDates;
    }
}
