package by.avdeev.task12.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixParser {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameters are {}, {}";
    private final static String RESULT = "return value is {}";
    private String regex = "[^-?\\d]";

    public List<List<String>> parse(List<String> strings) {
        logger.debug(START);
        logger.debug(PARAM, strings);
        List<List<String>> parsed = new ArrayList<>();
        for (String tempString : strings) {
            List<String> tempList = new ArrayList<>(Arrays.asList(tempString.split(regex)));
            parsed.add(tempList);
        }
        logger.debug(RESULT, parsed);
        return parsed;
    }
}
