package by.avdeev.pizzeria.command.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public abstract class TypeValidator {
    public abstract boolean validate(Map<String, Object> parameters);

    public static boolean validateRequest(HttpServletRequest request, Map<String, Object> parameters, Set<String> requiredParameters) {
        Logger logger = LogManager.getLogger();
        Map<String, String[]> requestMap = request.getParameterMap();
        Set<String> actualParameters = requestMap.keySet();
        logger.debug("req param={}", requiredParameters);
        logger.debug("actual param={}", actualParameters);
        if (!actualParameters.containsAll(requiredParameters)) {
            return false;
        }
        boolean isValid = true;
        for (Map.Entry<String, String[]> pair : requestMap.entrySet()) {
            if (pair.getValue().length > 0) {
                isValid = Arrays.stream(pair.getValue()).noneMatch(String::isEmpty);
                if (isValid) {
                    requestMap.forEach((key, value) -> parameters.put(key, value[0]));
                }
            }
        }
        logger.debug("param={}", parameters);
        return isValid;
    }
}
