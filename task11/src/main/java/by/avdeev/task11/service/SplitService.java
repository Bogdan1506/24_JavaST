package by.avdeev.task11.service;

import by.avdeev.task11.bean.Component;
import by.avdeev.task11.bean.Type;

import java.util.List;

public interface SplitService {
    List<String> split(Component component, Type dest);
}
