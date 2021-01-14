package filter;

import domain.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreatingCookieFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getAttribute("currentUser");
        Cookie cookie = new Cookie("user-login", user.getLogin());
        Cookie cookiePassword = new Cookie("user-password", user.getPassword());
        cookie.setMaxAge(60);
        cookiePassword.setMaxAge(60);
        response.addCookie(cookie);
        response.addCookie(cookiePassword);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
