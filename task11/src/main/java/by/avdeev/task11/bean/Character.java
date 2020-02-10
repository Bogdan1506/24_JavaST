package by.avdeev.task11.bean;

import java.util.List;

public class Character implements Component {
    private final static Type type = Type.CHARACTER;
    private char symbol;

    @Override
    public void addAll(List<Component> components) {
//        throw new UnsupportedOperationException();  //todo
    }

    @Override
    public String join() {
        return String.valueOf(symbol);
    }

    @Override
    public void setContent(String content) {
        if (content.equals("")) return;
        this.symbol = content.charAt(0);

    }

    @Override
    public Object getContent() {
        return symbol;
    }


    @Override
    public List<Component> getByType(Type type) {
        return null;
    }

    @Override
    public List<Component> getComponents() {
        return null;
    }

    @Override
    public void setComponents(List<Component> list) {

    }

    public Type getType() {
        return type;
    }


    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Character{" +
                "symbol=" + symbol +
                '}';
    }
}
