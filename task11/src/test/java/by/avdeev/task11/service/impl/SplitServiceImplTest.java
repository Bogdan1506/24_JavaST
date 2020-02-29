package by.avdeev.task11.service.impl;

import by.avdeev.task11.bean.Type;
import by.avdeev.task11.service.ServiceFactory;
import by.avdeev.task11.service.SplitService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.*;

public class SplitServiceImplTest {

    @DataProvider(name = "dP")
    public Iterator<Object[]> callDP() {
        Object[][] objects = {{"One.\nTwo.\nThree!", Arrays.asList("One.", "Two.", "Three!"), Type.PARAGRAPH},
                {"One. Two.", Arrays.asList("One.", "Two."), Type.SENTENCE},
                {"One, two.", Arrays.asList("One,", "two."), Type.LEXEME},
                {"One, two.", Arrays.asList("One", "two"), Type.WORD},
                {"One, two.", Arrays.asList(",", "."), Type.MARK},
                {"One.", Arrays.asList("O", "n", "e", "."), Type.CHARACTER}
        };
        List<Object[]> list = List.of(objects);
        return list.iterator();
    }

    @Test(dataProvider = "dP")
    public void testSplit(String string, List<String> list, Type type) {
        ServiceFactory serviceFactory = ServiceFactory.getFactory();
        SplitService splitService = serviceFactory.getSplitService();
        List<String> actual = new ArrayList<>(splitService.split(string, type));
        if (actual.get(0).equals("")) {
            actual.remove(0);
        }
        assertEquals(actual, list);
    }
}