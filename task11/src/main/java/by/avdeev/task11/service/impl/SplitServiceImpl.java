package by.avdeev.task11.service.impl;

import by.avdeev.task11.bean.Type;
import by.avdeev.task11.service.SplitService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class SplitServiceImpl implements SplitService {
    private EnumMap<Type, String> repository = new EnumMap<>(Type.class);
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameters are {}, {}";
    private final static String RESULT = "return value is {}";

    public SplitServiceImpl() {

        repository.put(Type.PARAGRAPH, "\n");
        repository.put(Type.SENTENCE, "(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?)\\s");
        repository.put(Type.LEXEME, "\\s");
        repository.put(Type.WORD, "[.?!, ']+");
        repository.put(Type.MARK, "[^,.!?']+");
        repository.put(Type.CHARACTER, "");
    }

    @Override
    public List<String> split(String element, Type dest) {
        logger.debug(START);
        logger.debug(PARAM, element, dest);
        List<String> result = Arrays.asList(element.split(repository.get(dest)));
        logger.debug(RESULT, result);
        return result;
    }
}
