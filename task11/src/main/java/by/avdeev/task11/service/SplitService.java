package by.avdeev.task11.service;

import by.avdeev.task11.bean.Type;

import java.util.List;

public interface SplitService {
    List<String> split(String element, Type dest);
}
