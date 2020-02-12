package by.avdeev.task11.parser;

import by.avdeev.task11.bean.Composite;
import by.avdeev.task11.bean.Type;
import by.avdeev.task11.bean.Character;
import by.avdeev.task11.bean.Component;
import by.avdeev.task11.service.ServiceFactory;
import by.avdeev.task11.service.SplitService;

import java.util.ArrayList;
import java.util.List;

public class CharacterParser implements Handler {
    private final static Type type = Type.CHARACTER;
    private Handler root;

    public CharacterParser(Handler root) {
        this.root = root;
    }

    public CharacterParser() {
    }

    @Override
    public void handleSplit(Component component) {
        ServiceFactory serviceFactory = ServiceFactory.getFactory();
        SplitService splitService = serviceFactory.getSplitService();
        List<String> parsed = splitService.split(component, type);
        List<Component> components = new ArrayList<>();
        for (String s : parsed) {
            Character temp = new Character();
            if (!s.equals("")) {
                temp.setSymbol(s.charAt(0));
            }
            components.add(temp);
        }
        ((Composite) component).addAll(components);
        ((Composite) component).setContent(null);
        if (root != null) {
            for (Component tempComponent : components) {
                root.handleSplit(tempComponent);
            }
        }
    }
}
