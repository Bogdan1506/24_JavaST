package by.avdeev.task02.numreplacement.service;

public class NumReplacementService {
    public void replaceNum(int[] integers) {
        if (integers[0] == integers[1]) {
            integers[0] = 0;
            integers[1] = 0;
        } else if (integers[0] > integers[1]) integers[1] = integers[0];
        else integers[0] = integers[1];
    }
}
