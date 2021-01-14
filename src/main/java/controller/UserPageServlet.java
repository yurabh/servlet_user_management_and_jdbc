package controller;

import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserPageServlet", urlPatterns = {"/user-page"})
public class UserPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        createPage(req, resp);
    }

    private void createPage(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();

        User user = (User) req.getAttribute("currentUser");

        writer.write("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <!-- Required meta tags -->\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n \n" +
                "    <!-- Bootstrap CSS -->\n" +
                "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\"\n" +
                "          integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\n" +
                "    <title>User-Profile-Page</title>\n" +
                "</head>\n" +
                "\n" +
                "<body class=\"bg-secondary\">");

        writer.write("<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n" +
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
                "      <form action=\"/exit-servlet\" method=\"post\">\n\" +" +
                "        <div class=\"form-inline my-2 my-lg-0\">\n" +
                "            <button class=\"btn btn-outline-success m-2 my-sm-0\" type=\"submit\" data-toggle=\"modal\"\n" +
                "                    data-target=\"#sing-in\">Exit\n" +
                "            </button>\n" +
                "        </div>\n" +
                "      </form>\n" +
                "    </div>\n" +
                "</nav>");

        writer.write("<div class=\"container w-50 mt-3 p-3\">\n" +
                "    <div class=\"card\">\n" +
                "        <div class=\"card-header bg-light p-2\">\n" +
                "            <div class=\"display-4 text-center\">User Info</div>\n" +
                "        </div>\n\n" +
                "        <div id=\"card-body\">\n" +
                "            <table class=\"table table-striped\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td class=\"text-center font-weight-bold\">First Name</td>\n" +
                "                    <td class=\"text-center\">" + user.getFirstName() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td class=\"text-center font-weight-bold\">Last Name</td>\n" +
                "                    <td class=\"text-center\">" + user.getLastName() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td class=\"text-center font-weight-bold\">Login</td>\n" +
                "                    <td class=\"text-center\">" + user.getLogin() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td class=\"text-center font-weight-bold\">Password</td>\n" +
                "                    <td class=\"text-center\">" + user.getPassword() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td class=\"text-center font-weight-bold\">Email</td>\n" +
                "                    <td class=\"text-center\">" + user.getEmail() + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td class=\"text-center font-weight-bold\">Status</td>\n" +
                "                    <td class=\"text-center\">" + user.getStatus() + "</td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "        \n" +
                "        <form action=\"/edit-profile\" method=\"post\">\n" +
                "            <input type=\"hidden\" name=\"lg\" value=\"" + user.getLogin() + "\"> \n" +
                "            <input type=\"hidden\" name=\"ps\" value=\"" + user.getPassword() + "\"> \n" +
                "            <input type=\"hidden\" name=\"em\" value=\"" + user.getEmail() + "\"> \n" +
                "            <input type=\"hidden\" name=\"st\" value=\"" + user.getStatus() + "\"> \n" +
                "            <input type=\"hidden\" name=\"fn\" value=\"" + user.getFirstName() + "\"> \n" +
                "            <input type=\"hidden\" name=\"ln\" value=\"" + user.getLastName() + "\"> \n" +
                "            <div class=\"card-footer d-flex justify-content-center\">\n" +
                "        <form action=\"/exit-servlet\" method=\"post\">\n" +
                "                <button type=\"submit\" class=\"btn btn-danger\" name=\"/exit-servlet\">Edit</button>\n" +
                "         </div>\n" +
                "        </form>\n" +
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
