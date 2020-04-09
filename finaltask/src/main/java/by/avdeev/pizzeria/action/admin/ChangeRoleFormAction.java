package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeRoleFormAction extends AdminAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        return null;
    }
}
