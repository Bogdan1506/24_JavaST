package by.avdeev.pizzeria.controller;

import by.avdeev.pizzeria.command.Command;
import by.avdeev.pizzeria.command.CommandManager;
import by.avdeev.pizzeria.command.CommandManagerFactory;
import by.avdeev.pizzeria.service.ServiceFactory;
import by.avdeev.pizzeria.transaction.TransactionFactory;
import by.avdeev.pizzeria.transaction.TransactionFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static by.avdeev.pizzeria.command.ConstantRepository.COMMAND;
import static by.avdeev.pizzeria.command.ConstantRepository.REDIRECTED_DATA;

@MultipartConfig
@WebServlet(urlPatterns = {"/product/*", "/user/*", "/profile/*", "/item/*", "/delivery/*", "/order/*", "/orderposition/*", "/local"})
public class ControllerServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(ControllerServlet.class);

    @Override
    public void init() throws ServletException {
        logger.info("Application has been started.");
        super.init();
    }

    @Override
    public void destroy() {
        logger.info("Application has been stopped.");
        super.destroy();
    }

    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp) {
        process(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp) {
        process(req, resp);
    }

    private void process(final HttpServletRequest request,
                         final HttpServletResponse response) {
        Command command = (Command) request.getAttribute(COMMAND);
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
        ServiceFactory serviceFactory;
        try (TransactionFactory transactionFactory = new TransactionFactoryImpl()) {
            serviceFactory = new ServiceFactory(transactionFactory);
            CommandManager commandManager = CommandManagerFactory.getManager(serviceFactory);
            Command.ForwardObject forwardObject;
            forwardObject = commandManager.execute(command, request, response);
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
                forwardPage = "/WEB-INF/jsp" + command.getName() + ".jsp";
                logger.debug("jspPage={}", forwardPage);
                try {
                    getServletContext().getRequestDispatcher(forwardPage).forward(request, response);
                } catch (IllegalArgumentException e) {
                    getServletContext().getRequestDispatcher("/WEB-INF/jsp/element/error.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            logger.error(e, e);
        }
    }
}
