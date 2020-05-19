package by.avdeev.pizzeria.action.admin.user;

import by.avdeev.pizzeria.action.admin.AdminAction;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.avdeev.pizzeria.action.ConstantRepository.ID;
import static by.avdeev.pizzeria.action.ConstantRepository.ILLEGAL_PARAMETERS;
import static by.avdeev.pizzeria.action.ConstantRepository.INCORRECT_TYPES;
import static by.avdeev.pizzeria.action.ConstantRepository.MESSAGE;
import static by.avdeev.pizzeria.action.ConstantRepository.POSITION_DELETED;

public class UserDeleteAction extends AdminAction {
    @Override
    public ForwardObject exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException {
        ForwardObject forwardObject = new ForwardObject("/user/list");
        UserService userService = factory.getUserService();
        int id;
        try {
            id = Integer.parseInt(request.getParameter(ID));
        } catch (IllegalArgumentException e) {
            forwardObject.getAttributes().putIfAbsent(MESSAGE, INCORRECT_TYPES);
            return forwardObject;
        }
        boolean idDeleted = userService.delete(id);
        if (idDeleted) {
            forwardObject.getAttributes().put(MESSAGE, POSITION_DELETED);
        } else {
            forwardObject.getAttributes().put(MESSAGE, ILLEGAL_PARAMETERS);
        }
        return forwardObject;
    }
}
