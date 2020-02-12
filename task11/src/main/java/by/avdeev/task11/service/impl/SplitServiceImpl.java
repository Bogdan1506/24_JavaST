package by.avdeev.task11.service.impl;

import by.avdeev.task11.bean.Component;
import by.avdeev.task11.bean.Composite;
import by.avdeev.task11.bean.Type;
import by.avdeev.task11.service.SplitService;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class SplitServiceImpl implements SplitService {
    private EnumMap<Type, String> repository = new EnumMap<>(Type.class);

    public SplitServiceImpl() {
        repository.put(Type.PARAGRAPH, "\n");
        repository.put(Type.SENTENCE, "(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?)\\s");
        repository.put(Type.LEXEME, "\\s");
        repository.put(Type.WORD, "\\W+");
        repository.put(Type.MARK, "[^,.!?]+");
        repository.put(Type.CHARACTER, "");
    }

    @Override
    public List<String> split(Component component, Type dest) {
        Composite composite = (Composite) component;
        String content = String.valueOf(composite.getContent());
        return Arrays.asList(content.split(repository.get(dest)));
    }
}
