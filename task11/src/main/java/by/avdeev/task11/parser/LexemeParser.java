package by.avdeev.task11.parser;

import by.avdeev.task11.bean.Type;
import by.avdeev.task11.bean.Component;
import by.avdeev.task11.bean.Composite;
import by.avdeev.task11.service.ServiceFactory;
import by.avdeev.task11.service.SplitService;

import java.util.ArrayList;
import java.util.List;

public class LexemeParser implements Handler {
    private final static Type type = Type.LEXEME;
    private Handler rootWord;
    private Handler rootMark;

    public LexemeParser(Handler rootWord) {
        this.rootWord = rootWord;
    }  //todo

    public LexemeParser(Handler rootWord, Handler rootMark) {
        this.rootWord = rootWord;
        this.rootMark = rootMark;
    }

    public LexemeParser() {
    }

    @Override
    public void handleSplit(Component component) {
        ServiceFactory serviceFactory = ServiceFactory.getFactory();
        SplitService splitService = serviceFactory.getSplitService();
        List<String> parsed = splitService.split(component, type);
        List<Component> components = new ArrayList<>();
        for (String s : parsed) {
            Composite temp = new Composite(type);
            temp.setContent(s);
            components.add(temp);
        }
        ((Composite) component).setComponents(components);
        ((Composite) component).setContent(null);
        if (rootWord != null) {
            for (Component tempComponent : components) {
                rootWord.handleSplit(tempComponent);
            }
        }
        if (rootMark != null) {
            for (Component tempComponent : components) {
                rootMark.handleSplit(tempComponent);
            }
        }
    }
}
