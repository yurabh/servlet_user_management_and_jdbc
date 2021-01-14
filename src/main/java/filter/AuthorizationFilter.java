package filter;

import dao.DaoGeneric;
import dao.dao_factory.MySqlDaoFactory;
import domain.User;
import exception.DaoException;
import dao.DaoFactory;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AuthorizationFilter implements Filter {

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
        if (user != null) {
            request.setAttribute("currentUser", user);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("wrongData.html");
        }
    }

    private User getUserFromDb(HttpServletRequest req) {
        String login = req.getParameter("lg");
        String password = req.getParameter("ps");
        User user = null;
        try {
            DaoFactory factory = new MySqlDaoFactory();
            DaoGeneric daoGeneric = factory.getDao(factory.getConnection(), User.class);
            user = (User) daoGeneric.read(login);
            System.out.println();
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void destroy() {
    }
}
