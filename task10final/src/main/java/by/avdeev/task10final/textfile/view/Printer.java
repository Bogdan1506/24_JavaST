package by.avdeev.task10final.textfile.view;

import by.avdeev.task10final.textfile.bean.TextFile;

import static java.lang.System.out;

public class Printer {
    public void printFile(TextFile textFile) {
        out.println(textFile);
    }

    public void printError() {
        out.println("The command is incorrect. Please, choose another");
    }
}
