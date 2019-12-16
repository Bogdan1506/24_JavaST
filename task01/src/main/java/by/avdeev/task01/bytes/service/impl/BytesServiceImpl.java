package by.avdeev.task01.bytes.service.impl;

import by.avdeev.task01.bytes.service.BytesService;
import by.avdeev.task01.bytes.service.exception.ServiceException;

import java.util.LinkedHashMap;
import java.util.Map;

public class BytesServiceImpl implements BytesService {
    @Override
    public Map<String, Double> convert(double bytes) throws ServiceException {
        if (bytes < 0) throw new ServiceException("Количество байтов меньше 0");
        Map<String, Double> infoUnits = new LinkedHashMap<>();
        infoUnits.put("килобайтов", bytes /= 1024);
        infoUnits.put("мегабайтов", bytes /= 1024);
        infoUnits.put("гигабайтов", bytes /= 1024);
        infoUnits.put("терабайтов", bytes /= 1024);
        infoUnits.put("петабайтов", bytes / 1024);
        return infoUnits;
    }
}