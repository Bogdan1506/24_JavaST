package by.avdeev.task12.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixParser {
    public List<List<String>> parse(List<String> strings) {
        List<List<String>> parsed = new ArrayList<>();
        for (String tempString : strings) {
            List<String> tempList = new ArrayList<>(Arrays.asList(tempString.split("[^-?\\d]")));
            parsed.add(tempList);
        }
        return parsed;
    }
}
