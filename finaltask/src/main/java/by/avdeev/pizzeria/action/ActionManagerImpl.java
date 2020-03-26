package by.avdeev.pizzeria.action;

import by.avdeev.pizzeria.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionManagerImpl implements ActionManager {
	private ServiceFactory factory;

	public ActionManagerImpl(ServiceFactory factory) {
		this.factory = factory;
	}

	public Action.Forward execute(Action action, HttpServletRequest request, HttpServletResponse response) {
		action.setFactory(factory);
		return action.exec(request, response);
	}

	@Override
	public void close() {
		factory.close();
	}
}
