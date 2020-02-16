package by.avdeev.task11.parser;

import by.avdeev.task11.bean.Type;
import by.avdeev.task11.bean.Component;
import by.avdeev.task11.bean.Composite;
import by.avdeev.task11.service.ServiceFactory;
import by.avdeev.task11.service.SplitService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class WordParser implements Handler {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameters are {}, {}";
    private final static Type type = Type.WORD;
    private Handler root;

    public WordParser(Handler root) {
        this.root = root;
    }

    public WordParser() {
    }

    @Override
    public void handleSplit(Component component, String content) {
        logger.debug(START);
        logger.debug(PARAM, component, content);
        ServiceFactory serviceFactory = ServiceFactory.getFactory();
        SplitService splitService = serviceFactory.getSplitService();
        List<String> parsed = splitService.split(content, type);
        for (String element : parsed) {
            Composite word = new Composite(type);
            ((Composite) component).add(word);
            if (root != null) {
                root.handleSplit(word, element);
            }
        }
    }
}
