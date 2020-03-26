package by.avdeev.pizzeria.controller;

import by.avdeev.pizzeria.action.Action;
import by.avdeev.pizzeria.action.ActionManager;
import by.avdeev.pizzeria.action.ActionManagerFactory;
import by.avdeev.pizzeria.dao.factory.TransactionFactory;
import by.avdeev.pizzeria.dao.factory.TransactionFactoryImpl;
import by.avdeev.pizzeria.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/final") //todo rename
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
        System.out.println("START");
        String name = request.getParameter("method");
        Action action = commandProvider.receiveCommand(name);
//        Action action = (Action) request.getAttribute("action");
//        try {
  /*          HttpSession session = request.getSession(false);
            if (session != null) {
                @SuppressWarnings("unchecked")
                Map<String, Object> attributes = (Map<String, Object>) session.getAttribute("redirectedData");
                if (attributes != null) {
                    for (String key : attributes.keySet()) {
                        request.setAttribute(key, attributes.get(key));
                    }
                    session.removeAttribute("redirectedData");
                }
            }*/
        ServiceFactory serviceFactory = ServiceFactory.getFactory();
        TransactionFactory transactionFactory = new TransactionFactoryImpl();  //creating connection
        serviceFactory.setTransactionFactory(transactionFactory);
//            serviceFactory.setTransactionFactory(transactionFactory);
        ActionManager actionManager = ActionManagerFactory.getManager(serviceFactory);
        Action.Forward forward = actionManager.execute(action, request, response);
        actionManager.close();
        System.out.println("FINISH");
     /*       if (session != null && forward != null && !forward.getAttributes().isEmpty()) {
                session.setAttribute("redirectedData", forward.getAttributes());
            }*/
  /*          String requestedUri = request.getRequestURI();
            if (forward != null && forward.isRedirect()) {
                String redirectedUri = request.getContextPath() + forward.getForward();
//                logger.debug(String.format("Request for URI \"%s\" id redirected to URI \"%s\"", requestedUri, redirectedUri));
                response.sendRedirect(redirectedUri);
            } else {
                String jspPage;
                if (forward != null) {
                    jspPage = forward.getForward();
                } else {
                    jspPage = action.getName() + ".jsp";
                }
                jspPage = "/WEB-INF/jsp" + jspPage;
//                logger.debug(String.format("Request for URI \"%s\" is forwarded to JSP \"%s\"", requestedUri, jspPage));
                getServletContext().getRequestDispatcher(jspPage).forward(request, response);
                request.setAttribute("error", "Ошибка обработки данных");
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }
        } catch (
                ServletException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
   *//*     catch (
                DAOException e) {
            e.printStackTrace();
        }*//*
    }*/
//        }
    }
}

    /*private void process(HttpServletRequest req, HttpServletResponse resp) {
//        HttpSession session = req.getSession(true);
        CommandProvider commandProvider = new CommandProvider();
        Command command = commandProvider.receiveCommand(req.getParameter("command"));
        try {
            command.execute(req, resp);
        } catch (ServiceException e) {
            e.printStackTrace();  //TODO add logger
        }
    }*/
