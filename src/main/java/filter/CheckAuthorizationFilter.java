package filter;

import dao.dao_factory.MySqlDaoFactory;
import domain.User;
import exception.DaoException;
import dao.DaoFactory;
import dao.DaoGeneric;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CheckAuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = getUserFromDb(request);
        RequestDispatcher dispatcher;
        if (user != null) {
            request.setAttribute("currentUser", user);
            dispatcher = request.getRequestDispatcher("/user-page");
            dispatcher.forward(request, response);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private User getUserFromDb(HttpServletRequest req) {
        Cookie[] cookie = req.getCookies();
        String login = null;
        String password = null;
        User user = null;
        if (cookie == null) {
            System.out.println("no cookies");
        } else {
            for (int i = 0; i < cookie.length; i++) {
                if (cookie[i].getName().equals("user-login")) {
                    login = cookie[i].getValue();
                }
                if (cookie[i].getName().equals("user-password")) {
                    password = cookie[1].getValue();
                }
            }

            if (login != null && password != null) {
                try {
                    DaoFactory factory = new MySqlDaoFactory();
                    DaoGeneric daoGeneric = factory.getDao(factory.getConnection(), User.class);
                    user = (User) daoGeneric.read(login);
                    if (user.getPassword().equals(password)) {
                        return user;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DaoException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void destroy() {
    }
}
