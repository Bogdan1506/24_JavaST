package by.avdeev.task01.bytes.service;

import by.avdeev.task01.bytes.service.exception.ServiceException;

import java.util.Map;

public interface BytesService {
    Map<String, Double> convert(double bytes) throws ServiceException;
}
