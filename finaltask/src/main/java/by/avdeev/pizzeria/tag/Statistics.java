package by.avdeev.pizzeria.tag;

import by.avdeev.pizzeria.service.DeliveryService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.ServiceFactory;
import by.avdeev.pizzeria.transaction.TransactionFactory;
import by.avdeev.pizzeria.transaction.TransactionFactoryImpl;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.sql.Date;

public class Statistics extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        TransactionFactory transactionFactory = null;
        try {
            transactionFactory = new TransactionFactoryImpl();
        } catch (ServiceException e) {
            throw new JspException(e.getMessage());
        }
        ServiceFactory serviceFactory = new ServiceFactory(transactionFactory);
        DeliveryService deliveryService = serviceFactory.getDeliveryService();
        int totalCount;
        int todayCount;
        Date date = new Date(System.currentTimeMillis());
        try {
            todayCount = deliveryService.findByDate(date);
            totalCount = deliveryService.countAll();
            String resTotal = String.format("<h6>Deliveries total: %d</h6>", totalCount);
            String resToday = String.format("</br><h6>Deliveries today: %d</h6>", todayCount);
            out.write(resTotal + resToday);
            transactionFactory.close();
        } catch (ServiceException | IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
