package by.avdeev.parser.controller;

import by.avdeev.parser.entity.Order;
import by.avdeev.parser.service.ParserService;
import by.avdeev.parser.service.ServiceException;
import by.avdeev.parser.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        ServiceFactory factory = ServiceFactory.getFactory();
        ParserService parserService = factory.getParserService();
        String pathname = req.getParameter("filename");
        String typeParser = req.getParameter("typeParser");
        Set<Order> orders = null;
        try {
            orders = parserService.parse(pathname, typeParser);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.setAttribute("type", typeParser);
        req.setAttribute("orders", orders);
        try {
            req.getRequestDispatcher("output.jsp").forward(req, resp);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
