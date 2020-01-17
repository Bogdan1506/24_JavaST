package by.avdeev.task10final.textfile.service;

import by.avdeev.task10final.textfile.bean.TextFile;

public class Validator {
    public boolean checkExtension(TextFile textFile) {
        return textFile.getChild().endsWith(TextFile.Extensions.TXT.toString().toLowerCase());
    }
}
