package by.avdeev.pizzeria.controller;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.ActionManager;
import by.avdeev.pizzeria.action.ActionManagerFactory;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.ServiceFactory;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/pza", "/index.html"})
public class ControllerServlet extends HttpServlet {
    private final CommandProvider commandProvider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("action");
        if (name == null) {
            name = "userShowList";
        }
        Action action = commandProvider.receiveCommand(name);
        HttpSession session = request.getSession(false);
        if (session != null) {
            @SuppressWarnings("unchecked")
            Map<String, Object> attributes = (Map<String, Object>) session.getAttribute("redirectedData");
            if (attributes != null) {
                for (String key : attributes.keySet()) {
                    request.setAttribute(key, attributes.get(key));
                }
                session.removeAttribute("redirectedData");   //получаем кучу атрибутов. их значение по ключу (заранее задаем названия ключей)
            }
        }
        ServiceFactory serviceFactory = ServiceFactory.getFactory();
        ActionManager actionManager = ActionManagerFactory.getManager(serviceFactory);
        Action.Forward forward = null;
        try {
            forward = actionManager.execute(action, request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (IncorrectFormDataException e) {
            e.printStackTrace();
        }
        if (session != null && forward != null && !forward.getAttributes().isEmpty()) {
            session.setAttribute("redirectedData", forward.getAttributes());
        }
        String requestedUri = request.getRequestURI();
        if (forward != null && forward.isRedirect()) {
            String redirectedUri = request.getContextPath() + "?action=" + forward.getForward();
//                logger.debug(String.format("Request for URI \"%s\" id redirected to URI \"%s\"", requestedUri, redirectedUri));
            response.sendRedirect(redirectedUri);
        } else {
            String jspPage;
            if (forward != null) {
                jspPage = forward.getForward();
            } else {
                jspPage = "jsp/" + action.getName() + ".jsp";
            }
//                logger.debug(String.format("Request for URI \"%s\" is forwarded to JSP \"%s\"", requestedUri, jspPage));
                request.getRequestDispatcher(jspPage).forward(request, response);
           /* request.setAttribute("error", "Ошибка обработки данных");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);*/
        }
    }
}
