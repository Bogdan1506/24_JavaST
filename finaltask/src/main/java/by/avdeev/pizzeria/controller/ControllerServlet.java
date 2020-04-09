package by.avdeev.pizzeria.controller;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.ActionManager;
import by.avdeev.pizzeria.action.ActionManagerFactory;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.ServiceFactory;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import by.avdeev.pizzeria.transaction.TransactionFactory;
import by.avdeev.pizzeria.transaction.TransactionFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/controller", "/index.html", "/product/*", "/user/*", "/profile/*"})
public class ControllerServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("started");
        Action action = (Action) request.getAttribute("action");
        HttpSession session = request.getSession(false);
        if (session != null) {
            @SuppressWarnings("unchecked")
            Map<String, Object> attributes = (Map<String, Object>) session.getAttribute("redirectedData");
            if (attributes != null) {
                for (String key : attributes.keySet()) {
                    request.setAttribute(key, attributes.get(key));
                }
                session.removeAttribute("redirectedData");
            }
        }
        TransactionFactory transactionFactory = new TransactionFactoryImpl();
        ServiceFactory serviceFactory = new ServiceFactory(transactionFactory);
        ActionManager actionManager = ActionManagerFactory.getManager(serviceFactory);
        Action.Forward forward = null;
        try {
            forward = actionManager.execute(action, request, response);
        } catch (ServiceException | IncorrectFormDataException e) {
            logger.error(e);
        }
        actionManager.close();
        if (session != null && forward != null && !forward.getAttributes().isEmpty()) {
            session.setAttribute("redirectedData", forward.getAttributes());
        }
        if (forward != null) {
            String redirectedUri = request.getContextPath() + forward.getRedirect();
            logger.debug("redirectedUri={}", redirectedUri);
            response.sendRedirect(redirectedUri);
        } else {
            String jspPage;
            logger.debug("servletContext={}", getServletContext().getContextPath());
            jspPage = "/WEB-INF/jsp" + action.getName() + ".jsp";
            logger.debug("jspPage={}", jspPage);
            getServletContext().getRequestDispatcher(jspPage).forward(request, response);
        }
    }
}
