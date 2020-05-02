package by.avdeev.pizzeria.controller.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "cookieReceive")
public class CookieReceiveFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Map<String, String> repCookies = new HashMap<>();
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            repCookies.put(cookie.getName(), cookie.getValue());
        }
        httpServletRequest.setAttribute("cookies", repCookies);
        filterChain.doFilter(httpServletRequest, servletResponse);
    }
}
