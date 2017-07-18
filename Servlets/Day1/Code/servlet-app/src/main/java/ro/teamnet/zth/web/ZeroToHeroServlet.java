package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Mihaela.Stoian on 7/18/2017.
 */
public class ZeroToHeroServlet extends HttpServlet {
    private String handleReques(HttpServletRequest req) {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String response = "Hello <b> "
                + firstName
                + " " + lastName + " </b>! Enjoy Zero To Hero!!!";

        return response;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("/text/html");
        resp.setContentType("/text/html");
    }
}
