package by.avdeev.task11.service.impl;

import by.avdeev.task11.bean.Character;
import by.avdeev.task11.bean.Component;
import by.avdeev.task11.bean.Composite;
import by.avdeev.task11.bean.Type;
import by.avdeev.task11.service.ServiceException;
import by.avdeev.task11.service.ServiceFactory;
import by.avdeev.task11.service.TextService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class TextServiceImplTest {
    private TextService textService;

    @BeforeClass
    public void setUp() {
        ServiceFactory factory = ServiceFactory.getFactory();
        textService = factory.getTextService();
    }

    @Test
    public void testJoinTree() {
        String path = "E:\\24_JavaST\\task11\\target\\files\\test.txt";
        String text = "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the with the release of Letraset sheets containing Lorem Ipsum passages, and more recently withdesktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.\n" +
                "It is a established fact that a reader will be of a page when looking at its layout.\n" +
                "Bye...";
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            fileOutputStream.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Component component = null;

        try {
            component = textService.createTree(path);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        String result = textService.joinTree(component);
        assertEquals(result, text);
    }

    @Test
    public void testFindComponent() {
        String path = "E:\\24_JavaST\\task11\\target\\files\\test.txt";
        String text = "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the with the release of Letraset sheets containing Lorem Ipsum passages, and more recently withdesktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.\n" +
                "It is a established fact that a reader will be of a page when looking at its layout.\n" +
                "Bye...";
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            fileOutputStream.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Component component = null;
        try {
            component = textService.createTree(path);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        Map<Integer, Component> repository = textService.receiveTextCollection();
        Component sameComponent = repository.get(0);
        assertEquals(sameComponent, component);
    }

    @Test
    public void testCreateTree() {
        Composite text = new Composite(Type.TEXT);
        List<Component> paragraphs = text.getComponents();
        Composite paragraph1 = new Composite(Type.PARAGRAPH);
        paragraphs.add(paragraph1);
        List<Component> sentences1 = paragraph1.getComponents();
        Composite sentence1 = new Composite(Type.SENTENCE);
        sentences1.add(sentence1);
        List<Component> lexemes1 = sentence1.getComponents();
        Composite lexeme1 = new Composite(Type.LEXEME);  //One!
        lexemes1.add(lexeme1);
        Composite word1 = new Composite(Type.WORD);
        List<Component> wordsAndMarks = lexeme1.getComponents();
        wordsAndMarks.add(word1);
        Composite mark1 = new Composite(Type.MARK);
        wordsAndMarks.add(mark1);
        List<Component> charsOfWords1 = word1.getComponents();
        List<Component> charsOfMark1 = mark1.getComponents();
        Character character1 = new Character();
        character1.setSymbol('O');
        Character character2 = new Character();
        character2.setSymbol('n');
        Character character3 = new Character();
        character3.setSymbol('e');
        charsOfWords1.add(character1);
        charsOfWords1.add(character2);
        charsOfWords1.add(character3);
        Character character4 = new Character();
        character4.setSymbol('!');
        charsOfMark1.add(character4);
        Composite paragraph2 = new Composite(Type.PARAGRAPH);
        paragraphs.add(paragraph2);
        List<Component> sentences2 = paragraph2.getComponents();
        Composite sentence2 = new Composite(Type.SENTENCE);
        sentences2.add(sentence2);
        List<Component> lexemes2 = sentence2.getComponents();
        Composite lexeme2 = new Composite(Type.LEXEME);  //One,
        lexemes2.add(lexeme2);
        Composite word2 = new Composite(Type.WORD);
        List<Component> wordsAndMarks2 = lexeme2.getComponents();
        wordsAndMarks2.add(word2);
        Composite mark2 = new Composite(Type.MARK);
        wordsAndMarks2.add(mark2);
        List<Component> charsOfWords2 = word2.getComponents();
        List<Component> charsOfMark2 = mark2.getComponents();
        Character character21 = new Character();
        character21.setSymbol('O');
        Character character22 = new Character();
        character22.setSymbol('n');
        Character character23 = new Character();
        character23.setSymbol('e');
        charsOfWords2.add(character21);
        charsOfWords2.add(character22);
        charsOfWords2.add(character23);
        Character character24 = new Character();
        character24.setSymbol(',');
        charsOfMark2.add(character24);
        Composite lexeme3 = new Composite(Type.LEXEME);  //two.
        lexemes2.add(lexeme3);
        Composite word3 = new Composite(Type.WORD);
        List<Component> wordsAndMarks3 = lexeme3.getComponents();
        wordsAndMarks3.add(word3);
        Composite mark3 = new Composite(Type.MARK);
        wordsAndMarks3.add(mark3);
        List<Component> charsOfWords3 = word3.getComponents();
        List<Component> charsOfMark3 = mark3.getComponents();
        Character character31 = new Character();
        character31.setSymbol('t');
        Character character32 = new Character();
        character32.setSymbol('w');
        Character character33 = new Character();
        character33.setSymbol('o');
        charsOfWords3.add(character31);
        charsOfWords3.add(character32);
        charsOfWords3.add(character33);
        Character character34 = new Character();
        character34.setSymbol('.');
        charsOfMark3.add(character34);
        Component actual = null;
        try {
            actual = textService.createTree("E:\\24_JavaST\\task11\\target\\files\\another-test.txt");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        assertEquals(actual, text);
    }
}