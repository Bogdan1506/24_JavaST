package by.avdeev.task04.season.controller;

import by.avdeev.task04.season.reader.Reader;
import by.avdeev.task04.season.service.SeasonService;
import by.avdeev.task04.season.service.exception.ServiceException;
import by.avdeev.task04.season.service.factory.ServiceFactory;

public class Controller {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final Reader reader = new Reader();

    public String executeTask() throws ServiceException {
        String season = reader.readSeason();
        SeasonService seasonService = serviceFactory.getSeasonService();
        return seasonService.defineSeason(season);
    }

    public static void main(String[] args) throws ServiceException {
        Controller controller = new Controller();
        System.out.println(controller.executeTask());
    }
}
