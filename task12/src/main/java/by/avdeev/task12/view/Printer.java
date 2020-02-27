package by.avdeev.task12.view;

import by.avdeev.task12.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import static java.lang.System.out;

public class Printer {
    private final Logger logger = LogManager.getLogger();
    private final static String START = "started";
    private final static String PARAM = "parameter is {}";

    public void printMatrix(Matrix matrix) {
        logger.debug(START);
        logger.debug(PARAM, matrix);
        out.println("LOLOLOLOLO\n" + matrix);
    }
}
