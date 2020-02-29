package by.avdeev.task11.service.impl;

import by.avdeev.task11.bean.Component;
import by.avdeev.task11.bean.Composite;
import by.avdeev.task11.bean.Type;
import by.avdeev.task11.dao.DAOException;
import by.avdeev.task11.dao.DAOFactory;
import by.avdeev.task11.dao.TextDAO;
import by.avdeev.task11.parser.CharacterParser;
import by.avdeev.task11.parser.Handler;
import by.avdeev.task11.parser.LexemeParser;
import by.avdeev.task11.parser.MarkParser;
import by.avdeev.task11.parser.ParagraphParser;
import by.avdeev.task11.parser.SentenceParser;
import by.avdeev.task11.parser.WordParser;
import by.avdeev.task11.service.ServiceException;
import by.avdeev.task11.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class TextServiceImpl implements TextService {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameter is {}";
    private final static String RESULT = "return value is {}";

    @Override
    public Map<Integer, Component> receiveTextCollection() {
        logger.debug(START);
        DAOFactory factory = DAOFactory.getFactory();
        TextDAO textDAO = factory.getTextDAO();
        Map<Integer, Component> result = textDAO.receiveTextCollection();
        logger.debug(RESULT, result);
        return result;
    }

    @Override
    public String joinTree(Component component) {
        logger.debug(START);
        logger.debug(PARAM, component);
        String temp = component.collect();
        String result = temp.substring(0, temp.length() - 1);
        logger.debug(RESULT, result);
        return result;
    }

    public Component findComponent(String key) {
        logger.debug(START);
        logger.debug(PARAM, key);
        int intKey = Integer.parseInt(key);
        DAOFactory factory = DAOFactory.getFactory();
        TextDAO textDAO = factory.getTextDAO();
        Component result = textDAO.readElement(intKey);
        logger.debug(RESULT, result);
        return result;
    }

    @Override
    public Component createTree(String pathname) throws ServiceException {
        logger.debug(START);
        logger.debug(PARAM, pathname);
        DAOFactory factory = DAOFactory.getFactory();
        TextDAO textDAO = factory.getTextDAO();
        List<String> strings;
        try {
            strings = textDAO.readFile(pathname);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        Composite text = new Composite(Type.TEXT);

        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string).append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String content = stringBuilder.toString();
        Handler parser6 = new CharacterParser();
        Handler parser5 = new MarkParser(parser6);
        Handler parser4 = new WordParser(parser6);
        Handler parser3 = new LexemeParser(parser4, parser5);
        Handler parser2 = new SentenceParser(parser3);
        Handler parser = new ParagraphParser(parser2);
        parser.handleSplit(text, content);
        textDAO.addTextObject(text);
        logger.debug(RESULT, text);
        return text;
    }
}
