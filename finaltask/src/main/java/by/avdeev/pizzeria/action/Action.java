package by.avdeev.pizzeria.action;

import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.ServiceFactory;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract public class Action {
    protected final static Logger logger = LogManager.getLogger();
    protected static final String MESSAGE = "message";
    private Set<Role> roles = new HashSet<>();
    private User authorizedUser;
    private String name;
    protected ServiceFactory factory;

    protected boolean validateRequest(HttpServletRequest request, Map<String, String> parameters, int parametersCount) {
        boolean isValid = false;
        Map<String, String[]> requestMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> requestPair = requestMap.entrySet();
        if (requestPair.size() < parametersCount) {
            return false;
        }
        for (Map.Entry<String, String[]> pair : requestPair) {
            if (pair.getValue().length > 0) {
                isValid = Arrays.stream(pair.getValue()).allMatch(a -> a != null && !a.isEmpty());
                if (isValid) {
                    requestMap.forEach((key, value) -> parameters.put(key, value[0]));
                }
            }
        }
        return isValid;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public User getAuthorizedUser() {
        return authorizedUser;
    }

    public void setAuthorizedUser(User authorizedUser) {
        this.authorizedUser = authorizedUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFactory(ServiceFactory factory) {
        this.factory = factory;
    }

    abstract public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException, IOException, ServletException;

    public static class ForwardObject {
        private String redirectUri;
        private Map<String, Object> attributes = new HashMap<>();

        public ForwardObject(String redirectUri) {
            this.redirectUri = redirectUri;
        }

        public void setRedirectUri(String redirectUri) {
            this.redirectUri = redirectUri;
        }

        public String getRedirectUri() {
            return redirectUri;
        }

        public Map<String, Object> getAttributes() {
            return attributes;
        }
    }
}
