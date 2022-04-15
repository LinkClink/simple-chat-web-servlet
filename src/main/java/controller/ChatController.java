package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Message;
import service.MessageService;
import service.impl.MessageServiceImpl;
import util.DataTimePatternUtil;

@WebServlet(urlPatterns = "/chat")
public class ChatController extends HttpServlet {
    private static final int MESSAGE_LIMIT = 50;
    private static final String USERNAME_PARAMETER = "username";
    private static final String ERROR_MESSAGE = "Please write message!";
    private static String username;
    private final MessageService messageService = new MessageServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String buttonParam = req.getParameter("button");
        if (buttonParam != null) {
            buttonListener(req, resp, buttonParam);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (username == null) {
            username = (String) session.getAttribute(USERNAME_PARAMETER);
        }
        req.setAttribute(USERNAME_PARAMETER, username);
        List<Message> messages = messageService.getLimitAsc(MESSAGE_LIMIT);
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/WEB-INF/views/chat.jsp").forward(req, resp);
    }

    private void sendMessage(String content) {
        Message message = new Message();
        message.setContent(content);
        message.setUsername(username);
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(DataTimePatternUtil.TIME_PATTERN);
        message.setDataTime(LocalDateTime.now().format(formatter));
        messageService.send(message);
    }

    private void buttonListener(HttpServletRequest req, HttpServletResponse resp,
                                String buttonParam) throws IOException {
        if (buttonParam.equals("send")) {
            String messageText = req.getParameter("message");
            if (!messageText.isEmpty()) {
                sendMessage(messageText);
                resp.sendRedirect(req.getContextPath() + "/chat");
            } else {
                req.setAttribute("errormsg", ERROR_MESSAGE);
                resp.sendRedirect(req.getContextPath() + "/chat");
            }
        } else if (buttonParam.equals("refresh")) {
            resp.sendRedirect(req.getContextPath() + "/chat");
        }
    }
}
