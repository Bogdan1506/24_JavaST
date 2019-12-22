package by.avdeev.task04decomposition.armstrongnum.controller;

import by.avdeev.task04decomposition.armstrongnum.service.ArmstrongNumService;
import by.avdeev.task04decomposition.armstrongnum.service.exception.ServiceException;
import by.avdeev.task04decomposition.armstrongnum.service.impl.ArmstrongNumServiceImpl;
import by.avdeev.task04decomposition.armstrongnum.view.Reader;
import by.avdeev.task04decomposition.armstrongnum.view.Writer;

import java.util.List;

public class Controller {  //№17
    private final ArmstrongNumService service = new ArmstrongNumServiceImpl();
    private final Reader reader = new Reader();
    private final Writer writer = new Writer();

    public void executeTask() throws ServiceException {
        int num = reader.readNum();
        List<Integer> res = service.findNum(num);
        writer.showNums(res);
    }

    public static void main(String[] args) throws ServiceException {
        Controller controller = new Controller();
        controller.executeTask();
    }
}
