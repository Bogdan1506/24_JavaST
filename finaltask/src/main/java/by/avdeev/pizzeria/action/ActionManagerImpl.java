package by.avdeev.pizzeria.action;

import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.ServiceFactory;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionManagerImpl implements ActionManager {
	private ServiceFactory factory;

	public ActionManagerImpl(ServiceFactory factory) {
		this.factory = factory;
	}

	public Action.Forward execute(Action action, HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
		action.setFactory(factory);
		return action.exec(request, response);
	}

}
