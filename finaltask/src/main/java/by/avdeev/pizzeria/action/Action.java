package by.avdeev.pizzeria.action;

import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract public class Action {
    protected final static Logger logger = LogManager.getLogger();
    protected static final String MESSAGE = "message";
    protected Map<String, Object> parameters = new HashMap<>();
    protected Map<String, String> invalidParameters = new HashMap<>();
    private Set<Role> roles = new HashSet<>();
    private String name;
    protected ServiceFactory factory;

    public Set<Role> getRoles() {
        return roles;
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

    abstract public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException, ServletException;

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
