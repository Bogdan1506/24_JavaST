package by.avdeev.pizzeria.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class RequestListener implements ServletRequestListener {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) sre.getServletRequest();
        logger.debug("request initialized, uri={}", httpServletRequest.getRequestURI());

    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) sre.getServletRequest();
        logger.debug("request destroyed, uri={}", httpServletRequest.getRequestURI());
    }
}
