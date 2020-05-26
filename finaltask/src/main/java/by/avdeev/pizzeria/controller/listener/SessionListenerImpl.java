package by.avdeev.pizzeria.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListenerImpl implements HttpSessionListener {
    private static final Logger logger = LogManager.getLogger(SessionListenerImpl.class);

    @Override
    public void sessionCreated(final HttpSessionEvent se) {
        logger.info("session created, id={}", se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent se) {
        logger.info("session destroyed, id={}", se.getSession().getId());
    }

}
