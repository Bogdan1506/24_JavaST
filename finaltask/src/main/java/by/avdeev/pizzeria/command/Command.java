package by.avdeev.pizzeria.command;

import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract public class Command {
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

    abstract public ForwardObject exec(final HttpServletRequest request,
                                       final HttpServletResponse response)
            throws ServiceException, IOException, ServletException;

    public static class ForwardObject {
        private String redirectUri;
        private Map<String, Object> attributes = new HashMap<>();

        public ForwardObject(String redirectUri) {
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
