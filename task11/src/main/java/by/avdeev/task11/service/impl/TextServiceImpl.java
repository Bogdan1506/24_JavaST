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

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class TextServiceImpl implements TextService {
    @Override
    public Map<Integer, Component> receiveTextCollection() {
        DAOFactory factory = DAOFactory.getFactory();
        TextDAO textDAO = factory.getTextDAO();
        return textDAO.receiveTextCollection();
    }

    @Override
    public String joinTree(String key) {
        int intKey = Integer.parseInt(key);
        DAOFactory factory = DAOFactory.getFactory();
        TextDAO textDAO = factory.getTextDAO();
        Component text = textDAO.readElement(intKey);
        return text.collect();
    }

    @Override
    public Component createTree(String pathname) throws ServiceException {
        DAOFactory factory = DAOFactory.getFactory();
        TextDAO textDAO = factory.getTextDAO();
        List<String> strings = null;
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
        String content = stringBuilder.toString();  //todo

        text.setContent(content);
        Handler parser6 = new CharacterParser();
        Handler parser5 = new MarkParser(parser6);
        Handler parser4 = new WordParser(parser6);
        Handler parser3 = new LexemeParser(parser4, parser5);
        Handler parser2 = new SentenceParser(parser3);
        Handler parser = new ParagraphParser(parser2);
        parser.handleSplit(text);
        textDAO.addTextObject(text);
        return text;
    }
}
