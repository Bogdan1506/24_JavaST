package by.avdeev.pizzeria.controller;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.ActionManager;
import by.avdeev.pizzeria.action.ActionManagerFactory;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.ServiceFactory;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
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

@WebServlet(urlPatterns = {"/index.html", "/product/*", "/user/*", "/profile/*", "/item/*", "/delivery/*"})
public class ControllerServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    private static final String REDIRECTED_DATA = "redirectedData";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("started");
        Action action = (Action) request.getAttribute("action");
        HttpSession session = request.getSession(true);
        if (session != null) {
            @SuppressWarnings("unchecked")
            Map<String, Object> attributes = (Map<String, Object>) session.getAttribute(REDIRECTED_DATA);
            logger.debug("redirectedData={}", attributes);
            if (attributes != null) {
                for (Map.Entry<String, Object> pair : attributes.entrySet()) {
                    String key = pair.getKey();
                    request.setAttribute(key, attributes.get(key));
                }
                logger.debug("attributes session ={}", attributes);
                session.removeAttribute(REDIRECTED_DATA);
            }
        }
        ServiceFactory serviceFactory = new ServiceFactory(new TransactionFactoryImpl());
        ActionManager actionManager = ActionManagerFactory.getManager(serviceFactory);
        Action.ForwardObject forwardObject = null;
        try {
            forwardObject = actionManager.execute(action, request, response);
        } catch (ServiceException | IncorrectFormDataException e) {
            logger.error(e);
        }
        actionManager.close();
        if (session != null && forwardObject != null && !forwardObject.getAttributes().isEmpty()) {
            session.setAttribute(REDIRECTED_DATA, forwardObject.getAttributes());
        }
        if (forwardObject != null) {
            String redirectedUri = forwardObject.getRedirectUri();
            logger.debug("redirectedUri={}", redirectedUri);
            response.sendRedirect(redirectedUri);
        } else {
            String forwardPage;
            logger.debug("servletContext={}", getServletContext().getContextPath());
            forwardPage = "/WEB-INF/jsp" + action.getName() + ".jsp";
            logger.debug("jspPage={}", forwardPage);
            try {
                getServletContext().getRequestDispatcher(forwardPage).forward(request, response);
            } catch (IllegalArgumentException e) {
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/element/error.jsp").forward(request, response);
            }
        }
    }
}
