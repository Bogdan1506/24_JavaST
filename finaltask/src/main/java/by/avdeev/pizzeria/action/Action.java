package by.avdeev.pizzeria.action;

import by.avdeev.pizzeria.entity.Role;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.ServiceFactory;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract public class Action {
    private Set<Role> roles = new HashSet<>();
    private User authorizedUser;
    private String name;
    protected ServiceFactory factory;

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

    abstract public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException;

    public static class Forward {
        private String redirect;
        private Map<String, Object> attributes = new HashMap<>();

        public Forward(String redirect) {
            this.redirect = redirect;
        }

		public void setRedirect(String redirect) {
			this.redirect = redirect;
		}

		public String getRedirect() {
            return redirect;
        }

        public Map<String, Object> getAttributes() {
            return attributes;
        }
    }
}
