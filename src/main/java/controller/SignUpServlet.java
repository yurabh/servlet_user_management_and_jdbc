package controller;

import dao.DaoGeneric;
import dao.dao_factory.MySqlDaoFactory;
import domain.User;
import domain.UserState;
import exception.DaoException;
import dao.DaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SignUpServlet", urlPatterns = {"/registration"})
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher;
        String page;
        User user = collectUserInfo(req);
        User userSaved = saveToDb(user);
        if (userSaved != null) {
            req.setAttribute("currentUser", user);
            page = "/user-page";
        } else {
            page = "/failed-registration.html";
        }
        dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }

    private User saveToDb(User user) {
        User user1 = new User();
        try {
            DaoFactory factory = new MySqlDaoFactory();
            DaoGeneric daoGeneric = factory.getDao(factory.getConnection(), User.class);
            user1 = (User) daoGeneric.read(user.getLogin());
            if (user1 == null) {
                user = (User) daoGeneric.create(user);
                return user;
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

    private User collectUserInfo(HttpServletRequest req) {
        User user = null;
        String fn = req.getParameter("fn");
        String sn = req.getParameter("sn");
        String lg = req.getParameter("lg");
        String ps = req.getParameter("ps");
        String cps = req.getParameter("cps");
        String em = req.getParameter("em");
        if (ps.equals(cps)) {
            user = new User();
            user.setFirstName(fn);
            user.setLastName(sn);
            user.setLogin(lg);
            user.setPassword(ps);
            user.setEmail(em);
            user.setStatus(UserState.NEW);
        }
        return user;
    }
}
