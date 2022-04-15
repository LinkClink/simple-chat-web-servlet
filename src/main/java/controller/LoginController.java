package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private static final String USERNAME_PARAMETER = "username";
    private static final String ERROR_MESSAGE = "Please write username!";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter(USERNAME_PARAMETER);
        if (username.isEmpty()) {
            req.setAttribute("errormsg", ERROR_MESSAGE);
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute(USERNAME_PARAMETER, username);
        resp.sendRedirect(req.getContextPath() + "/chat");
    }
}
