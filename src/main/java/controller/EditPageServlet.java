package controller;

import domain.User;
import domain.UserState;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EditPageServlet", urlPatterns = {"/edit-profile"})
public class EditPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        createEditPage(getOptions(req), resp);
    }

    private User getOptions(HttpServletRequest req) {
        String password = req.getParameter("ps");
        String firstName = req.getParameter("fn");
        String lastName = req.getParameter("ln");
        String email = req.getParameter("em");
        String status = req.getParameter("st");
        String login = req.getParameter("lg");
        System.out.println();
        User user = new User();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setStatus(UserState.valueOf(status));
        return user;
    }

    private void createEditPage(User user, HttpServletResponse resp)
            throws IOException {

        PrintWriter writer = resp.getWriter();

        resp.setContentType("text/html");

        writer.write("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <!-- Required meta tags -->\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n\n" +
                "    <!-- Bootstrap CSS -->\n" +
                "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\"\n" +
                "          integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\n" +
                "    <title>EditUserPage</title>\n" +
                "</head>");

        writer.write("<body class=\"bg-secondary\">\n" +
                "\n" +
                "<!--Navigation menu-->\n" +
                "<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n" +
                "    <a class=\"navbar-brand\" href=\"#\">Online Shop</a>\n" +
                "    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarTogglerDemo02\"\n" +
                "            aria-controls=\"navbarTogglerDemo02\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                "        <span class=\"navbar-toggler-icon\"></span>\n" +
                "    </button>\n" +
                "    <div class=\"collapse navbar-collapse\" id=\"navbarTogglerDemo02\">\n" +
                "        <ul class=\"navbar-nav mr-auto mt-2 mt-lg-0\">\n" +
                "            <li class=\"nav-item\">\n" +
                "                <a class=\"nav-link\" href=\"#\">Link</a>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "        <div class=\"form-inline my-2 my-lg-0\">\n" +
                "        <form action=\"/exit-servlet\" method=\"post\">\n\" +" +
                "            <button class=\"btn btn-outline-success m-2 my-sm-0\" type=\"submit\" data-toggle=\"modal\"\n" +
                "                   data-target=\"#sing-in\">Exit\n" +
                "            </button>\n" +
                "        </form>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</nav>");

        writer.write("<div class=\"container w-50 mt-3 p-3\">\n" +
                "    <div class=\"card\">\n" +
                "        <div class=\"card-header bg-light p-2\">\n" +
                "            <div class=\"display-4 text-center\">EDIT PROFILE PAGE</div>\n" +
                "        </div>\n" +
                "        <form action=\"/update-user-data\" method=\"post\">\n\n" +
                "            <div id=\"card-body\">\n" +
                "                <table class=\"table table-striped\">\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td class=\"text-center font-weight-bold\">First Name</td>\n" +
                "                        <td class=\"w-50\"><input type=\"text\" class=\"form-control\" name=\"fn\" value=\"" + user.getFirstName() + "\" >\n</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td class=\"text-center font-weight-bold\">Last Name</td>\n" +
                "                        <td class=\"w-50\"><input type=\"text\" class=\"form-control\" name=\"ln\" value=\"" + user.getLastName() + "\"></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td class=\"text-center font-weight-bold\">Login</td>\n" +
                "                        <td class=\"w-50\"><input type=\"text\" class=\"form-control\" disabled=\"disabled\" value=\"" + user.getLogin() + "\">" +
                "                            <input type=\"hidden\" class=\"form-control\" name=\"lg\" value=\"" + user.getLogin() + "\"></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td class=\"text-center font-weight-bold\">Password</td>\n" +
                "                        <td class=\"w-50\"><input type=\"password\" class=\"form-control\" id=\"inputPassword\" name=\"ps\"\n value=\"" + user.getPassword() + "\"></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td class=\"text-center font-weight-bold\">Email</td>\n" +
                "                        <td class=\"w-50\"><input type=\"email\" class=\"form-control\" id=\"colFormLabel\" name=\"em\"\n value=\"" + user.getEmail() + "\">\n </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td class=\"text-center font-weight-bold\">Status</td>\n" +
                "                        <td class=\"w-50\"><input type=\"text\" disabled=\"disabled\" class=\"form-control\" id=\"colFormLabel1\"\n value=\"" + user.getStatus() + "\">" +
                "                        <input type=\"hidden\" class=\"form-control\" id=\"colFormLabel1\"\n name=\"st\"\" value=\"" + user.getStatus() + "\"></td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "            </div>\n \n \n" +
                "            <div class=\"card-footer d-flex justify-content-center\">\n" +
                "                <button type=\"submit\" class=\"btn btn-danger\" name=\"bt\">Save</button>\n" +
                "            </div>\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</div>");

        writer.write("<!-- Optional JavaScript -->\n" +
                "<!-- jQuery first, then Popper.js, then Bootstrap JS -->\n" +
                "<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\"\n" +
                "        integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\"\n" +
                "        crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\"\n" +
                "        integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\"\n" +
                "        crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\"\n" +
                "        integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\"\n" +
                "        crossorigin=\"anonymous\"></script>\n" +
                "</body>\n" +
                "</html>");
    }
}
