package by.avdeev.task11.bean;

public class Character implements Component {
    private final static Type type = Type.CHARACTER;
    private char symbol;

    @Override
    public String collect() {
        return String.valueOf(symbol);
    }

    public Type getType() {
        return type;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Character{" +
                symbol +
                '}';
    }
}
