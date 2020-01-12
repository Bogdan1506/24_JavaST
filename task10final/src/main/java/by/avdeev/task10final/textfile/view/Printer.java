package by.avdeev.task10final.textfile.view;

import java.util.stream.Stream;

import static java.lang.System.out;

public class Printer {
    public void printFile(Stream<String> stream) {
        stream.forEach(System.out::println);
    }
}
