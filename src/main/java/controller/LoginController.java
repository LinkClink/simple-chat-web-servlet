package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private static final String USER_ID_ATTRIBUTE = "userId";
    private static final String USERNAME_ATTRIBUTE = "username";
    private static final String ERROR_MESSAGE = "Please write correct username!";
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter(USERNAME_ATTRIBUTE);
        Optional<User> user = userService.getByUsername(username);
        if (username == null || user.isEmpty()) {
            req.setAttribute("errormsg", ERROR_MESSAGE);
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute(USER_ID_ATTRIBUTE, user.get().getId());
        resp.sendRedirect(req.getContextPath() + "/chat");
    }
}
