package by.avdeev.pizzeria.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class DeliveryTag extends TagSupport {
    private int countTotal;
    private int countToday;

    public void setCountTotal(int countTotal) {
        this.countTotal = countTotal;
    }

    public void setCountToday(int countToday) {
        this.countToday = countToday;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        String resTotal = String.format("<h6>Deliveries total: %d</h6>", countTotal);
        String resToday = String.format("</br><h6>Deliveries today: %d</h6>", countToday);
        try {
            out.write(resTotal + resToday);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
