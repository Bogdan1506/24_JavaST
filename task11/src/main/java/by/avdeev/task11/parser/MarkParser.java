package by.avdeev.task11.parser;

import by.avdeev.task11.bean.Type;
import by.avdeev.task11.bean.Component;
import by.avdeev.task11.bean.Composite;
import by.avdeev.task11.service.ServiceFactory;
import by.avdeev.task11.service.SplitService;

import java.util.ArrayList;
import java.util.List;

public class MarkParser implements Handler {
    private final static Type type = Type.MARK;
    private Handler root;

    public MarkParser(Handler root) {
        this.root = root;
    }

    public MarkParser() {
    }

    @Override
    public void handleSplit(Component component) {
        ServiceFactory serviceFactory = ServiceFactory.getFactory();
        SplitService splitService = serviceFactory.getSplitService();
        List<String> parsed = splitService.split(component, type);
        List<Component> components = new ArrayList<>();
        for (String s : parsed) {
            if (s.equals("")) continue;
            Component temp = new Composite(type);
            temp.setContent(s);
            components.add(temp);
        }
        component.addAll(components);
        if (root != null) {
            for (Component tempComponent : components) {
                root.handleSplit(tempComponent);
            }
        }
    }
}
