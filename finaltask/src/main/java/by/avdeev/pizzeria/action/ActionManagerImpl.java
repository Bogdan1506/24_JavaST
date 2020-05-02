package by.avdeev.pizzeria.action;

import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.ServiceFactory;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionManagerImpl implements ActionManager {
	private ServiceFactory factory;

	@Override
	public void close() throws ServiceException {
		factory.close();
	}

	public ActionManagerImpl(ServiceFactory factory) {
		this.factory = factory;
	}

	public Action.ForwardObject execute(Action action, HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException, IOException, ServletException {
		action.setFactory(factory);
		return action.exec(request, response);
	}

}
