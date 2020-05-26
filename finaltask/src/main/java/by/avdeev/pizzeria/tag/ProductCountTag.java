package by.avdeev.pizzeria.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ProductCountTag extends TagSupport {
    private String head;
    private int rows;

    public void setHead(String head) {
        this.head = head;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("<table border='1'><colgroup span='2' title='title' />");
            out.write("<thead><tr><th scope='col'>" + head + "</th></tr></thead>");
            out.write("<tbody><tr><td>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody() throws JspTagException {
        if (rows-- > 1) {
            try {
                pageContext.getOut().write("</td></tr><tr><td>");
            } catch (IOException e) {
                throw new JspTagException(e.getMessage());
            }
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() throws JspTagException {
        try {
            pageContext.getOut().write("</td></tr></tbody></table>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_PAGE;
    }
}
