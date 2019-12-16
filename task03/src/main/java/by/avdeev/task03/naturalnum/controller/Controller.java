package by.avdeev.task03.naturalnum.controller;

import by.avdeev.task03.naturalnum.reader.Reader;
import by.avdeev.task03.naturalnum.service.NaturalNumService;
import by.avdeev.task03.naturalnum.service.exception.ServiceException;

public class Controller { //№24
    private final NaturalNumService service = new NaturalNumService();
    private final Reader reader = new Reader();

    public int[] executeTask() throws ServiceException {
        int natNum = reader.readInt();
        int[] integers = service.makeArray(natNum);
        int sumInt = service.sum(integers);
        int convertedInt = service.convert(integers);
        return new int[]{sumInt, convertedInt};
    }

    public static void main(String[] args) throws ServiceException {
        Controller controller = new Controller();
        int[] res = controller.executeTask();
        System.out.printf("Сумма цифр = %d%nПреобразованное число = %d", res[0], res[1]);
    }
}
