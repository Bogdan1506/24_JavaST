package by.avdeev.pizzeria.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeliveryTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger(DeliveryTag.class);
    private int countTotal;
    private int countToday;

    public void setCountTotal(int countTotal) {
        this.countTotal = countTotal;
    }

    public void setCountToday(int countToday) {
        this.countToday = countToday;
    }

    @Override
    public int doStartTag() {
        JspWriter out = pageContext.getOut();
        String resTotal = String.format("<h6>Deliveries total: %d</h6>", countTotal);
        String resToday = String.format("</br><h6>Deliveries today: %d</h6>", countToday);
        try {
            out.write(resTotal + resToday);
        } catch (IOException e) {
            logger.error(e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
