package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Mihaela.Stoian on 7/19/2017.
 */
public class HttpSessionLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter printWriter = response.getWriter();
        if(username.equals("admin") && password.equals("admin")) {
            printWriter.println("Welcome back <b>" +
                    username
                    +" " + "!</b> "
            + "<p>ID: " + session.getId() + " .</p>");
        } else {
            session.setAttribute("username", username);
            session.setAttribute("session", session);
            request.getRequestDispatcher("/views/loginFail.jsp").forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
