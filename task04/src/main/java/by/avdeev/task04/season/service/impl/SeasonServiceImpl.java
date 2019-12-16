package by.avdeev.task04.season.service.impl;

import by.avdeev.task04.season.bean.Season;
import by.avdeev.task04.season.service.SeasonService;
import by.avdeev.task04.season.service.exception.ServiceException;

public class SeasonServiceImpl implements SeasonService {
    @Override
    public String defineSeason(String month) throws ServiceException {
        Season season;
        switch (month) {
            case "December":
            case "January":
            case "February":
                season = Season.Winter;
                break;
            case "March":
            case "April":
            case "May":
                season = Season.Spring;
                break;
            case "June":
            case "July":
            case "August":
                season = Season.Summer;
                break;
            case "September":
            case "October":
            case "November":
                season = Season.Autumn;
                break;
            default:
                throw new ServiceException();
        }
        return season.toString();
    }
}
