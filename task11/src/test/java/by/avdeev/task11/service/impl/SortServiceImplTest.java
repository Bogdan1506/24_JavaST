package by.avdeev.task11.service.impl;

import by.avdeev.task11.bean.Component;
import by.avdeev.task11.bean.Composite;
import by.avdeev.task11.bean.Type;
import by.avdeev.task11.service.ServiceException;
import by.avdeev.task11.service.ServiceFactory;
import by.avdeev.task11.service.SortService;
import by.avdeev.task11.service.TextService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class SortServiceImplTest {
    private TextService textService;
    private SortService sortService;

    @BeforeClass
    public void setUp() {
        ServiceFactory serviceFactory = ServiceFactory.getFactory();
        sortService = serviceFactory.getSortService();
        textService = serviceFactory.getTextService();
    }

    @Test
    public void testSortWords() {
        String sentence = "It is a established fact that a reader will be of a page when looking at its layout.";
        List<String> actual = sortService.sortWords(sentence);
        List<String> expected = Arrays.asList("a", "a", "a", "It", "is", "be", "of", "at", "its", "fact", "that", "will", "page", "when", "reader",
                "layout", "looking", "established");
        assertEquals(actual, expected);
    }

    @Test
    public void testSortLexemes() {
    }

    @Test
    public void testSortParagraphs() {
        String text = "One.\nTwo. Three. Four.\nFive. Six.";
        String path = "E:\\24_JavaST\\task11\\target\\files\\one-two.txt";
        FileOutputStream fileOutputStream;
        List<Component> components;
            List<Component> actual;
        try {
            fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(text.getBytes());
            Component component = textService.createTree(path);
            components = ((Composite) component).getByType(Type.PARAGRAPH);
            actual = sortService.sortParagraphs(component);
        } catch (IOException | ServiceException e) {
            e.printStackTrace();
        }

    }
}